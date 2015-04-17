package contracto

import contracto.api.ContractoService
import contracto.discovery.ContractoMethodFinder
import contracto.handler.DefaultContractsWithMatchHandler
import contracto.handler.DefaultContractsWithoutMatchHandler
import contracto.handler.DefaultMethodsWithoutMatchHandler
import contracto.matcher.ContractMatcher
import contracto.model.contract.Contract
import contracto.model.reflect.ContractoMethod
import groovy.transform.CompileStatic

@CompileStatic
class Contracto {
    private ContractoService service = new ContractoService()
    private ContractoMethodFinder methodExtractor = new ContractoMethodFinder()
    private ContractMatcher matcher = new ContractMatcher()
    private DefaultContractsWithoutMatchHandler contractsWithoutMatchHandler = new DefaultContractsWithoutMatchHandler()
    private DefaultContractsWithMatchHandler contractsWithMatchHandler = new DefaultContractsWithMatchHandler()
    private DefaultMethodsWithoutMatchHandler methodsWithoutMatchHandler = new DefaultMethodsWithoutMatchHandler()

    boolean checkContracts(List<Class> apis, List<String> urls) {
        List<Contract> contracts = service.downloadContracts(urls)
        List<ContractoMethod> retrofitMethods = methodExtractor.findRetrofitMethods(apis)
        return contractsWithoutMatchHandler.handle(matcher.findContractsWithoutMatch(retrofitMethods, contracts)) &&
                methodsWithoutMatchHandler.handle(matcher.findMethodsWithoutMatch(retrofitMethods, contracts)) &&
                contractsWithMatchHandler.handle(matcher.findMatching(retrofitMethods, contracts))
    }

}
