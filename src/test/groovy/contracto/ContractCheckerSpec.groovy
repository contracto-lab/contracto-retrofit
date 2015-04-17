package contracto

import client.data.MyData
import spock.lang.Specification

class ContractCheckerSpec extends Specification {

    def "Should return true when object match body"() {
        expect:
        ContractChecker.checkObjectMatchBody(MyData, ContractStub.body())
    }

    def "Should return false when object doesn't match body"() {
        given:
        def body = ContractStub.body()
        body.embedded.first().name = 'new_name_just_to_fail_checking'
        expect:
        !ContractChecker.checkObjectMatchBody(MyData, body)
    }
}
