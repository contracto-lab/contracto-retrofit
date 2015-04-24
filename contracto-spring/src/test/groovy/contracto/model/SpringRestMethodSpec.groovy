package contracto.model

import spock.lang.Specification

import static org.springframework.web.bind.annotation.RequestMethod.GET
import static org.springframework.web.bind.annotation.RequestMethod.POST

class SpringRestMethodSpec extends Specification {

    def "Should create distinct set of http methods for given http methods collections"() {
        expect:
            SpringRestMethod.allHttpMethods(classHttpMethod, methodHttpMethod).containsAll(result)
        where:
            classHttpMethod   | methodHttpMethod  | result
            [POST]            | [POST]            | [POST]
            [POST]            | []                | [POST]
            []                | []                | [GET]
            [GET,POST]        | []                | [GET,POST]
            [GET]             | [POST]            | [GET,POST]
            []                | [GET,POST]        | [GET,POST]
    }
}
