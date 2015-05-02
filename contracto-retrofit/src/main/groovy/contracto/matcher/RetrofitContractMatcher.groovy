package contracto.matcher

import contracto.model.HttpMethod
import contracto.model.RetrofitPath
import contracto.model.contract.Contract
import contracto.model.reflect.ContractoMethod
import groovy.transform.CompileStatic

import java.lang.reflect.Method

@CompileStatic
class RetrofitContractMatcher implements ContractMatcherFinder.ContractMatcher {

    @Override
    boolean isMatching(Contract contract, Method method) {
        return HttpMethod.of(method).name() == contract.getRequestMethod() &&
                RetrofitPath.from(method).matches(contract.getRequestPath())
    }
}
