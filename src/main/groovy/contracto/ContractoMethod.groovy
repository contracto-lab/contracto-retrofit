package contracto

import groovy.transform.CompileStatic

import java.lang.reflect.Method

@CompileStatic
class ContractoMethod {
    final Method method
    final Class fromClass

    ContractoMethod(Method method, Class fromClass) {
        this.method = method
        this.fromClass = fromClass
    }
}
