package contracto

import contracto.api.ContractoService
import contracto.handler.ContractoMethodFinder
import contracto.handler.MatchResultHandler
import contracto.matcher.ContractMatcher
import contracto.model.MatchResult
import contracto.model.contract.Contract
import contracto.model.reflect.ContractoMethod
import groovy.transform.CompileStatic

@CompileStatic
class Contracto {
    private ContractoService service = new ContractoService()
    private ContractoMethodFinder methodFinder = new ContractoMethodFinder()
    private ContractMatcher matcher = new ContractMatcher()
    private MatchResultHandler matchesHandler = new MatchResultHandler()

    boolean checkContracts(Collection<Class> controllers, Collection<String> urls) {
        Collection<Contract> contracts = service.downloadContracts(urls)
        Collection<ContractoMethod> controllerMethods = methodFinder.findMethods(controllers)
        MatchResult matchResult = matcher.calculateMatchResult(controllerMethods, contracts)
        return matchesHandler.isSuccessfullyMatched(matchResult)
    }
}
