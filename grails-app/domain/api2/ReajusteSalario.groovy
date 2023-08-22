package api2

import grails.gorm.annotation.Entity
import javax.persistence.ManyToOne
import java.time.LocalDate


@Entity
class ReajusteSalario {

    LocalDate dataReajuste
    BigDecimal valorSalario

    @ManyToOne
    Funcionario funcionario

    static constraints = {
        dataReajuste blank: false
        valorSalario blank: false
        funcionario blank: false
        id increment: true
        version : false
    }

}