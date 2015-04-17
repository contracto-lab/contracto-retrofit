package contracto

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

import java.lang.reflect.Method

@CompileStatic
@EqualsAndHashCode
class ContractMethodMatch {
    Method method
    Contract contract

    boolean match() {
        return ContractChecker.checkClassMatchItem(method.returnType,contract.request.meta.response.body)
    }
}
