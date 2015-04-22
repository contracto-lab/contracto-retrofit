package contracto.handler

import contracto.model.ContractMethodMatch
import contracto.model.MatchResult
import groovy.transform.CompileStatic

@CompileStatic
class MatchResultHandler {
    private ContractsWithMatchHandler contractsWithMatchHandler
    private DefaultContractsWithoutMatchHandler contractsWithoutMatchHandler = new DefaultContractsWithoutMatchHandler()
    private DefaultMethodsWithoutMatchHandler methodsWithoutMatchHandler = new DefaultMethodsWithoutMatchHandler()

    boolean isSuccessfullyMatched(MatchResult matchResult) {
        return contractsWithoutMatchHandler.handle(matchResult.unmatchedContracts) &&
                methodsWithoutMatchHandler.handle(matchResult.unmatchedMethods) &&
                contractsWithMatchHandler.handle(matchResult.matches)
    }

    interface ContractsWithMatchHandler{
        boolean handle(Collection<ContractMethodMatch> matches)
    }
}
