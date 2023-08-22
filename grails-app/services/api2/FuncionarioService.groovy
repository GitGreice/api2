package api2

import grails.gorm.transactions.Transactional

@Transactional
class FuncionarioService {

    def listarFuncionarios() {
        Funcionario.list()
    }

    def buscarFuncionario(Long id) {
        Funcionario.get(id)
    }

    def salvarFuncionario(Funcionario funcionario) {
        funcionario.validate()
        if (funcionario.hasErrors()) {
            return funcionario.errors
        }
        funcionario.save(flush: true, failOnError: true)
    }

    def atualizarFuncionario(Funcionario funcionario) {
        funcionario.validate()
        if (funcionario.hasErrors()) {
            return funcionario.errors
        }
        funcionario.save(flush: true, failOnError: true)
    }

    def apagarFuncionario(Funcionario funcionario) {
        funcionario.delete(flush: true, failOnError: true)
    }
}