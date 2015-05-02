package contracto

import contracto.api.ContractoService
import contracto.discovery.ContractoMethodFinder
import contracto.discovery.SpringAnnotatedMethodsFinder
import contracto.handler.ContractMatcherFinder
import contracto.handler.MatchResultHandler
import contracto.handler.SpringContractsWithMatchHandler
import contracto.matcher.SpringContractMatcher
import contracto.model.MatchResult
import contracto.model.contract.Contract
import groovy.transform.CompileStatic

import java.lang.reflect.Method

@CompileStatic
class Contracto {
    private ContractoService service = new ContractoService()
    private ContractoMethodFinder methodExtractor = new ContractoMethodFinder(new SpringAnnotatedMethodsFinder())
    private ContractMatcherFinder matcher = new ContractMatcherFinder(new SpringContractMatcher())
    private MatchResultHandler matchesHandler = new MatchResultHandler(new SpringContractsWithMatchHandler())

    boolean checkContracts(List<Class> apis, List<String> urls) {
        List<Contract> contracts = service.downloadContracts(urls)
        List<Method> retrofitMethods = methodExtractor.findMethods(apis)
        MatchResult matchResult = matcher.calculateMatchResult(retrofitMethods, contracts)
        return matchesHandler.isSuccessfullyMatched(matchResult)
    }

}
