package contracto

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

@CompileStatic
@EqualsAndHashCode
class ContractMethodMatch {
    ContractoMethod method
    Contract contract

    boolean match() {
        return ContractChecker.checkClassMatchItem(method.method.returnType,contract.request.meta.response.body)
    }
}
