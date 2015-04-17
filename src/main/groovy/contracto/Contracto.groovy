package contracto

import contracto.api.ContractoService
import contracto.matcher.ContractMatcher
import contracto.model.contract.Contract
import groovy.transform.CompileStatic

@CompileStatic
class Contracto {
    static boolean checkContracts(List<Class> apis, List<String> urls) {
        List<Contract> contracts = new ContractoService().call(urls)
        return checkContractsWithContracts(apis, contracts)
    }

    private static boolean checkContractsWithContracts(List<Class> apis, List<Contract> contracts) {
        ContractMatcher matcher = new ContractMatcher(apis, contracts)
        matcher.findContractsWithoutMatch()*.displayWarning()
        return matcher.findMatching().every { it.match() }
    }

}
