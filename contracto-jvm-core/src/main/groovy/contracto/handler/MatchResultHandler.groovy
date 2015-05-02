package contracto.handler

import contracto.model.MatchResult
import groovy.transform.CompileStatic

@CompileStatic
class MatchResultHandler {
    private final DefaultContractsWithoutMatchHandler contractsWithoutMatchHandler =
            new DefaultContractsWithoutMatchHandler()
    private final DefaultMethodsWithoutMatchHandler methodsWithoutMatchHandler = new DefaultMethodsWithoutMatchHandler()
    private final DefaultContractsWithMatchHandler contractsWithMatchHandler

    MatchResultHandler(DefaultContractsWithMatchHandler contractsWithMatchHandler) {
        this.contractsWithMatchHandler = contractsWithMatchHandler
    }

    boolean isSuccessfullyMatched(MatchResult matchResult) {
        return contractsWithoutMatchHandler.handle(matchResult.unmatchedContracts) &&
                methodsWithoutMatchHandler.handle(matchResult.unmatchedMethods) &&
                contractsWithMatchHandler.handle(matchResult.matches)
    }
}
