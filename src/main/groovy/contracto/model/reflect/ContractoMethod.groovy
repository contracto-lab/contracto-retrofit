package contracto.model.reflect

import groovy.transform.Canonical
import groovy.transform.CompileStatic

import java.lang.reflect.Method

@Canonical
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
