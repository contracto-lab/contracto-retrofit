package contracto.api

import contracto.model.ContractStub
import contracto.model.contract.Contract
import spock.lang.Specification

class ContractoServiceSpec extends Specification {

    def "should return same contract"() {
        given:
        List<Contract> contracts = new ContractoService().downloadContracts([
                "http://localhost:13579/contracts/my_data.con.json",
                "http://localhost:13579/contracts/other_data.con.json",
        ])
        expect:
        contracts == [ContractStub.contract(), ContractStub.otherContract()]
    }
}
