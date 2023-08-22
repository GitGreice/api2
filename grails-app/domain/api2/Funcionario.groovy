package api2

import grails.gorm.annotation.Entity
import javax.persistence.ManyToOne

@Entity
class Funcionario {

    String nome

    @ManyToOne
    Cidade cidade

    static constraints = {
        nome blank: false, maxSize: 50
        id increment: true
        version : false
    }
}