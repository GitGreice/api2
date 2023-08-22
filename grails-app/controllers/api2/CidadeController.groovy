package api2

import grails.converters.JSON
import grails.transaction.Transactional

@Transactional
class CidadeController {

    def cidadeService

    def index() {
        if (request.method != 'GET') {
            render status: 423, contentType: 'application/json', text: '{"error": "Só pode usar o método GET aqui."}'
            return
        }

        def cidades = cidadeService.listarCidades()
        render cidades as JSON
    }

    def show(Cidade cidadeobj) {
        render cidadeobj as JSON
    }

    @Transactional
    def save() {
        if (request.method != 'POST') {
            render status: 423, contentType: 'application/json', text: '{"error": "Só pode usar o método POST aqui."}'
            return
        }

        def contentType = request.getContentType()
        if (contentType && contentType.contains('application/json')) {
            def cidade = new Cidade(params)
            if (cidade.validate()) {
                cidadeService.salvarCidade(cidade)
            } else {
                respondWithError(cidade.errors)
            }
            respond([message: "Cidade cadastrada"], status: 200)
        }
    }

    def update(Cidade cidadeobj) {
        if (request.method != 'PUT') {
            render status: 405, contentType: 'application/json', text: '{"error": "Só pode usar o método PUT aqui."}'
            return
        }

        def contentType = request.getContentType()
        if (contentType && contentType.contains('application/json')) {
            if (cidadeobj.validate()) {
                cidadeService.atualizarCidade(cidadeobj)
                render cidadeobj as JSON
            } else {
                respondWithError(cidadeobj.errors)
            }
        }
    }

    def delete() {
        if (request.method != 'DELETE') {
            render status: 423, contentType: 'application/json', text: '{"error": "Só pode usar o método DELETE aqui."}'
            return
        }

        def id = params.id
        if (!id) {
            render status: 400, contentType: 'application/json', text: '{"error": "o ID não pode ser vazio ou nulo."}'
            return
        }

        def cidadeobj = Cidade.get(id)
        if (!cidadeobj) {
            render status: 404, contentType: 'application/json', text: '{"error": "Registro não encontrado."}'
            return
        }

        cidadeService.apagarCidade(cidadeobj)
        render status: 204
    }

    def get() {
        if (request.method != 'GET') {
            render status: 423, contentType: 'application/json', text: '{"error": "Só pode usar o método GET aqui."}'
            return
        }

        def id = params.id
        if (!id) {
            render status: 400, contentType: 'application/json', text: '{"error": "o ID não pode ser vazio ou nulo."}'
            return
        }

        def cidadeobj = Cidade.get(id)
        if (!cidadeobj) {
            render status: 404, contentType: 'application/json', text: '{"error": "Registro não encontrado."}'
            return
        }

        render cidadeobj as JSON
    }

    private respondWithError(errors) {
        render status: 400, contentType: 'application/json', text: errors as JSON
    }
}