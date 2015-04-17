package contracto

import client.data.MyData
import spock.lang.Specification

class ContractCheckerSpec extends Specification {

    def "Should return true when object match body"() {
        expect:
        ContractChecker.checkObjectMatchBody(MyData, ContractStub.body())
    }
}
