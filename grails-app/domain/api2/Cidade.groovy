package api2
import grails.gorm.annotation.Entity

@Entity
class Cidade {

    String nome
    static mapping = {
        id increment: true
        version false
    }
}