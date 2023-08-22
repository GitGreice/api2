package api2

class BootStrap {

    CidadeService cidadeService
    FuncionarioService funcionarioService


    def init = { servletContext ->
        dadosCidade()
        dadosFuncionario()
    }

    def dadosCidade() {
        def cidades = ["Araricá", "Sapiranga", "Dois Irmãos"]

        cidades.each { nome ->
            def cidade = new Cidade(nome: nome)
            cidadeService.salvarCidade(cidade)
        }
    }

    def dadosFuncionario() {
        def nomesFuncionarios = ["Nara", "Greice", "Bina"]

        nomesFuncionarios.each { nome ->
            def funcionario = new Funcionario(nome: nome)
            funcionarioService.salvarFuncionario(funcionario)
        }
    }

    def destroy = {

    }

}