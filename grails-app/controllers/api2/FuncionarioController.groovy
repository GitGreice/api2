package api2

import grails.converters.JSON
import grails.transaction.Transactional

@Transactional
class FuncionarioController {

    def funcionarioService

    def index() {
        if (request.method != 'GET') {
            render status: 423, contentType: 'application/json', text: '{"error": "Só pode usar o método GET aqui.."}'
            return
        }

        def funcionarios = funcionarioService.listarFuncionarios()
        render funcionarios as JSON
    }

    def show(Funcionario funcionarioobj) {
        render funcionarioobj as JSON
    }

    def save() {
        if (request.method != 'POST') {
            render status: 423, contentType: 'application/json', text: '{"error": "Só pode usar o método POST aqui.."}'
            return
        }

        def contentType = request.getContentType()
        if (contentType && contentType.contains('application/json')) {
            def funcionario = new Funcionario(params)
            if (funcionario.validate()) {
                funcionarioService.salvarFuncionario(funcionario)
                render funcionario as JSON
            } else {
                respondWithError(funcionario.errors)
            }
        } else {
            render status: 415, contentType: 'application/json', text: '{"error": "Só pode usar JSON aqui."}'
        }
    }

    def update() {
        if (request.method != 'PUT') {
            render status: 423, contentType: 'application/json', text: '{"error": "Só pode usar o método PUT aqui.."}'
            return
        }

        def id = params.id
        if (!id) {
            render status: 400, contentType: 'application/json', text: '{"error": "ID não pode ser vazio ou nulo."}'
            return
        }

        def contentType = request.getContentType()
        if (contentType && contentType.contains('application/json')) {
            def funcionarioobj = Funcionario.get(id)
            if (!funcionarioobj) {
                render status: 424, contentType: 'application/json', text: '{"error": "Registro não encontrado."}'
                return
            }

            funcionarioobj.properties = params
            if (funcionarioobj.validate()) {
                funcionarioService.atualizarFuncionario(funcionarioobj)
                render funcionarioobj as JSON
            } else {
                respondWithError(funcionarioobj.errors)
            }
        }
    }

    def delete() {
        if (request.method != 'DELETE') {
            render status: 423, contentType: 'application/json', text: '{"error": Só pode usar o método DELETE aqui.."}'
            return
        }

        def id = params.id
        if (!id) {
            render status: 400, contentType: 'application/json', text: '{"error": "Id não pode ser vazio ou nulo."}'
            return
        }

        def funcionarioobj = Funcionario.get(id)
        if (!funcionarioobj) {
            render status: 424, contentType: 'application/json', text: '{"error": "Registro não encontrado."}'
            return
        }

        funcionarioService.apagarFuncionario(funcionarioobj)
        render status: 204
    }

    private respondWithError(errors) {
        render status: 400, contentType: 'application/json', text: errors as JSON
    }
}