package contracto.handler

import groovy.transform.CompileStatic

import java.lang.reflect.Method

@CompileStatic
class DefaultMethodsWithoutMatchHandler {
    boolean failOnMethodsWithoutMatch = false

    boolean handle(List<Method> contractoMethods) {
        return contractoMethods.isEmpty() ? true : !failOnMethodsWithoutMatch
    }
}
