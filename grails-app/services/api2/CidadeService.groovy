package api2

import grails.gorm.transactions.Transactional

@Transactional
class CidadeService {

    def listarCidades() {
        Cidade.list()
    }
    def salvarCidade(Cidade cidade) {
        cidade.save()
    }
    def atualizarCidade(Cidade cidade) {
        cidade.merge()
    }
    def apagarCidade(Cidade cidade) {
        cidade.delete()
    }

}