package contracto.api

import contracto.model.ContractStub
import contracto.model.contract.Contract
import spock.lang.Specification

class ContractoServiceSpec extends Specification {

    def "should return same contract"() {
        given:
        List<Contract> contracts = new ContractoService().downloadContracts([
                "https://raw.githubusercontent.com/contracto-lab/contracto-retrofit/master/contracts/my_data.con.json",
                "https://raw.githubusercontent.com/contracto-lab/contracto-retrofit/master/contracts/other_data.con.json",
        ])
        expect:
        contracts == [ContractStub.contract(), ContractStub.otherContract()]
    }
}
