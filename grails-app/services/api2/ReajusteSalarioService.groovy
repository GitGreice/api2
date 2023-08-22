package api2

import grails.gorm.transactions.Transactional
import api2.ReajusteSalario

@Transactional
class ReajusteSalarioService {

    def listarReajustesSalario() {
        ReajusteSalario.list()
    }

    def buscarReajusteSalario(Long id) {
        ReajusteSalario.get(id)
    }

    def salvarReajusteSalario(ReajusteSalario reajuste) {
        reajuste.validate()
        if (reajuste.hasErrors()) {
            return reajuste.errors
        }
        reajuste.save(flush: true, failOnError: true)
    }

    def atualizarReajusteSalario(ReajusteSalario reajuste) {
        reajuste.validate()
        if (reajuste.hasErrors()) {
            return reajuste.errors
        }
        reajuste.save(flush: true, failOnError: true)
    }

    def apagarReajusteSalario(ReajusteSalario reajuste) {
        reajuste.delete(flush: true, failOnError: true)
    }
}