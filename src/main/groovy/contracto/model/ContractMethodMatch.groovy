package contracto.model

import contracto.matcher.ContractChecker
import contracto.model.reflect.ContractoMethod
import contracto.model.contract.Contract
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

@CompileStatic
@EqualsAndHashCode
class ContractMethodMatch {
    ContractoMethod method
    Contract contract

    boolean match() {
        return ContractChecker.checkMethodReturnTypeMatchItem(method,contract.request.meta.response.body)
    }
}
