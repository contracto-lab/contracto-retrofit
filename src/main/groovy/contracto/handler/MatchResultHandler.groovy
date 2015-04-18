package contracto.handler

import contracto.model.MatchResult
import groovy.transform.CompileStatic

@CompileStatic
class MatchResultHandler {
    private DefaultContractsWithoutMatchHandler contractsWithoutMatchHandler = new DefaultContractsWithoutMatchHandler()
    private DefaultContractsWithMatchHandler contractsWithMatchHandler = new DefaultContractsWithMatchHandler()
    private DefaultMethodsWithoutMatchHandler methodsWithoutMatchHandler = new DefaultMethodsWithoutMatchHandler()

    boolean isSuccessfullyMatched(MatchResult matchResult) {
        return contractsWithoutMatchHandler.handle(matchResult.unmatchedContracts) &&
                methodsWithoutMatchHandler.handle(matchResult.unmatchedMethods) &&
                contractsWithMatchHandler.handle(matchResult.matches)
    }
}
