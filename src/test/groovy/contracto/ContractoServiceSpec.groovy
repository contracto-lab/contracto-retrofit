package contracto

import contracto.api.ContractoService
import spock.lang.Specification

class ContractoServiceSpec extends Specification {

    def "should return same contract"() {
        given:
        Contract contract = new ContractoService().call()
        expect:
        contract == ContractStub.contract()
    }
}
