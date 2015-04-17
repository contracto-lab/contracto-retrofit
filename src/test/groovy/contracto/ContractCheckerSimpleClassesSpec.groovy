package contracto

import spock.lang.Specification

import static contracto.Type.*

class ContractCheckerSimpleClassesSpec extends Specification {

    def "Should recognise correct simple classes as number"() {
        expect:
        ContractChecker.checkClassMatchItem(aClass, new Item(type: type)) == result
        where:
        type   | aClass  | result
        number | Integer | true
        number | int     | true
        number | Number  | true
        number | double  | true
        number | String  | false
        number | Boolean | false
        number | boolean | false
    }

    def "Should recognise correct simple classes as string"() {
        expect:
        ContractChecker.checkClassMatchItem(aClass, new Item(type: type)) == result
        where:
        type   | aClass  | result
        string | Integer | false
        string | int     | false
        string | Number  | false
        string | double  | false
        string | String  | true
        string | Boolean | false
        string | boolean | false
    }

    def "Should recognise correct simple classes as boolean"() {
        expect:
        ContractChecker.checkClassMatchItem(aClass, new Item(type: type)) == result
        where:
        type | aClass  | result
        bool | Integer | false
        bool | int     | false
        bool | Number  | false
        bool | double  | false
        bool | String  | false
        bool | Boolean | true
        bool | boolean | true
    }
}