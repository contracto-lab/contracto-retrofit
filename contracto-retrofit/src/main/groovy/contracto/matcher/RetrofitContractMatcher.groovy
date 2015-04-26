package contracto.matcher

import contracto.model.HttpMethod
import contracto.model.RetrofitPath
import contracto.model.contract.Contract
import contracto.model.reflect.ContractoMethod
import groovy.transform.CompileStatic

@CompileStatic
class RetrofitContractMatcher implements ContractMatcherFinder.ContractMatcher {

    @Override
    boolean isMatching(Contract contract, ContractoMethod method) {
        return HttpMethod.of(method.method).name() == contract.getRequestMethod() &&
                RetrofitPath.from(method.method).matches(contract.getRequestPath())
    }
}
