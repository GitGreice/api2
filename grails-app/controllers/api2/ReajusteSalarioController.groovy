package api2

import grails.converters.JSON
import grails.transaction.Transactional
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Transactional
class ReajusteSalarioController {

    def reajusteSalarioService

    def index() {
        if (request.method != 'GET') {
            render status: 405, contentType: 'application/json', text: '{"error": "Só pode usar o método GET aqui.."}'
            return
        }

        def reajustes = reajusteSalarioService.listarReajustesSalario()
        render reajustes as JSON
    }

    def show(ReajusteSalario reajusteSalarioobj) {
        render reajusteSalarioobj as JSON
    }

    def save() {
        if (request.method != 'POST') {
            render status: 423, contentType: 'application/json', text: '{"error": "Só pode usar o método POST aqui.."}'
            return
        }

        def contentType = request.getContentType()
        if (contentType && contentType.contains('application/json')) {
            def reajusteSalario = new ReajusteSalario(params)

            if (reajusteSalario.validate()) {
                reajusteSalarioService.salvarReajusteSalario(reajusteSalario)
                render reajusteSalario as JSON
            } else {
                respondWithError(reajusteSalario.errors)
            }
        } else {
            render status: 415, contentType: 'application/json', text: '{"error": "Só pode JSON."}'
        }
    }

    def update() {
        if (request.method != 'PUT') {
            render status: 423, contentType: 'application/json', text: '{"error": "Só pode usar o método PUT aqui.."}'
            return
        }

        def id = params.id
        if (!id) {
            render status: 400, contentType: 'application/json', text: '{"error": "Id não pode ser nulo ou vazio."}'
            return
        }

        def reajusteSalarioobj = ReajusteSalario.get(id)
        if (!reajusteSalarioobj) {
            render status: 424, contentType: 'application/json', text: '{"error": "Registro não encontrado."}'
            return
        }

        reajusteSalarioobj.properties = params
        if (reajusteSalarioobj.validate()) {
            reajusteSalarioService.atualizarReajusteSalario(reajusteSalarioobj)
            render reajusteSalarioobj as JSON
        } else {
            respondWithError(reajusteSalarioobj.errors)
        }
    }

    def delete() {
        if (request.method != 'DELETE') {
            render status: 423, contentType: 'application/json', text: '{"error": "Só pode usar o método DELETE aqui.."}'
            return
        }

        def id = params.id
        if (!id) {
            render status: 400, contentType: 'application/json', text: '{"error": "ID não pode ser vazio ou nulo."}'
            return
        }

        def reajusteSalarioobj = ReajusteSalario.get(id)
        if (!reajusteSalarioobj) {
            render status: 424, contentType: 'application/json', text: '{"error": "Registro não encontrado."}'
            return
        }

        reajusteSalarioService.apagarReajusteSalario(reajusteSalarioobj)
        render status: 204
    }

    private respondWithError(errors) {
        render status: 400, contentType: 'application/json', text: errors as JSON
    }
}