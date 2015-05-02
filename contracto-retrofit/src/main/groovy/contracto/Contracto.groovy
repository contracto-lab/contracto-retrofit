package contracto

import contracto.api.ContractoService
import contracto.discovery.ContractoMethodFinder
import contracto.discovery.RetrofitAnnotatedMethodsFinder
import contracto.handler.MatchResultHandler
import contracto.handler.RetrofitContractsWithMatchHandler
import contracto.matcher.ContractMatcherFinder
import contracto.matcher.RetrofitContractMatcher
import contracto.model.MatchResult
import contracto.model.contract.Contract
import groovy.transform.CompileStatic

import java.lang.reflect.Method

@CompileStatic
class Contracto {
    private ContractoService service = new ContractoService()
    private ContractoMethodFinder methodExtractor = new ContractoMethodFinder(new RetrofitAnnotatedMethodsFinder())
    private ContractMatcherFinder matcher = new ContractMatcherFinder(new RetrofitContractMatcher())
    private MatchResultHandler matchesHandler = new MatchResultHandler(new RetrofitContractsWithMatchHandler())

    boolean checkContracts(List<Class> apis, List<String> urls) {
        List<Contract> contracts = service.downloadContracts(urls)
        List<Method> retrofitMethods = methodExtractor.findMethods(apis)
        MatchResult matchResult = matcher.calculateMatchResult(retrofitMethods, contracts)
        return matchesHandler.isSuccessfullyMatched(matchResult)
    }

}
