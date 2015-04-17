package contracto

import groovy.transform.CompileStatic

import java.lang.reflect.Method

@CompileStatic
class ContractoMethod {
    final Method method
    final Class inClass

    ContractoMethod(Method method, Class inClass) {
        this.method = method
        this.inClass = inClass
    }

    ContractoClassType getReturnType() {
        return ContractoClassType.fromMethod(method, inClass)
    }
}
