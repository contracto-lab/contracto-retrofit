package contracto

import client.api.MyApi
import spock.lang.Specification

class ResponseBodySpec extends Specification {

    def ""() {
        given:
        Contract contract = ContractStub.contract()
        expect:
        ContractChecker.checkBody(MyApi.class.methods.first(), contract.request.meta.response.body)
    }
}
