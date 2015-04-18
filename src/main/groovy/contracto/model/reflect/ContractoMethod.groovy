package contracto.model.reflect

import groovy.transform.CompileStatic

import java.lang.reflect.Method

@CompileStatic
class ContractoMethod {

    final Method method

    ContractoMethod(Method method) {
        this.method = method
    }

    ContractoClassType getReturnType() {
        return ContractoClassType.fromMethod(method)
    }
}
