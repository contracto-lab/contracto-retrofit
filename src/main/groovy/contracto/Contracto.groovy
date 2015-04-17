package contracto

import contracto.api.ContractoService
import contracto.discovery.ContractoMethodFinder
import contracto.matcher.ContractMatcher
import contracto.model.contract.Contract
import groovy.transform.CompileStatic

@CompileStatic
class Contracto {
    private ContractoService service = new ContractoService()
    private ContractoMethodFinder methodExtractor = new ContractoMethodFinder()

    boolean checkContracts(List<Class> apis, List<String> urls) {
        List<Contract> contracts = service.downloadContracts(urls)
        ContractMatcher matcher = new ContractMatcher(methodExtractor.findMethods(apis), contracts)
        matcher.findContractsWithoutMatch()*.displayWarning()
        return matcher.findMatching().every { it.match() }
    }


}
