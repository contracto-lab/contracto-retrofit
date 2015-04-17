package contracto.handler

import contracto.model.reflect.ContractoMethod
import groovy.transform.CompileStatic

@CompileStatic
class DefaultMethodsWithoutMatchHandler {
    boolean failOnMethodsWithoutMatch = false

    boolean handle(List<ContractoMethod> contractoMethods) {
        return contractoMethods.isEmpty() ? true : !failOnMethodsWithoutMatch
    }
}
