package contracto

import contracto.api.ContractoService
import spock.lang.Specification

class ContractoServiceSpec extends Specification {

    def "should return same contract"() {
        given:
        List<Contract> contracts = new ContractoService().call([
                "https://raw.githubusercontent.com/kv109/contracto_sample-contract/master/my_data.con.json",
        ])
        expect:
        contracts == [ContractStub.contract()]
    }
}
