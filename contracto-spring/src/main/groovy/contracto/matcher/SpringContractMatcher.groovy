package contracto.matcher

import contracto.model.SpringRest
import contracto.model.contract.Contract
import contracto.model.reflect.ContractoMethod
import groovy.transform.CompileStatic

@CompileStatic
class SpringContractMatcher implements ContractMatcherFinder.ContractMatcher {

    @Override
    boolean isMatching(Contract contract, ContractoMethod method) {
        return SpringRest.from(method.method).matches(contract.request.path)
    }
}
