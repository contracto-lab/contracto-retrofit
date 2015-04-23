package contracto.matcher

import contracto.model.SpringRestMethod
import contracto.model.SpringRestPath
import contracto.model.contract.Contract
import contracto.model.reflect.ContractoMethod
import groovy.transform.CompileStatic

@CompileStatic
class SpringContractMatcher implements ContractMatcherFinder.ContractMatcher {

    @Override
    boolean isMatching(Contract contract, ContractoMethod method) {
        return SpringRestMethod.from(method.method).matches(contract.request.httpMethod) &&
                SpringRestPath.from(method.method).matches(contract.request.path)
    }
}
