package contracto.handler

import contracto.model.contract.Contract
import groovy.transform.CompileStatic

@CompileStatic
class DefaultContractsWithoutMatchHandler {
    boolean failOnContractsWithoutMatch = false;

    boolean handle(Collection<Contract> contracts) {
        contracts*.displayWarning()
        return contracts.isEmpty() ? true : !failOnContractsWithoutMatch;
    }
}
