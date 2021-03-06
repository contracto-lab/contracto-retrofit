package contracto.matcher

import contracto.handler.ContractMatcherFinder
import contracto.model.SpringRestMethod
import contracto.model.SpringRestPath
import contracto.model.contract.Contract
import groovy.transform.CompileStatic

import java.lang.reflect.Method

@CompileStatic
class SpringContractMatcher implements ContractMatcherFinder.ContractMatcher {

    @Override
    boolean isMatching(Contract contract, Method method) {
        return SpringRestMethod.from(method).matches(contract.requestMethod) &&
                SpringRestPath.from(method).matches(contract.requestPath)
    }
}
