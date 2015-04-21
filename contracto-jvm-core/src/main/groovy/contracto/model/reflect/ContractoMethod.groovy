package contracto.model.reflect

import groovy.transform.Canonical
import groovy.transform.CompileStatic

import java.lang.reflect.Method

@Canonical
@CompileStatic
abstract class ContractoMethod {

    final Method method

    ContractoMethod(Method method) {
        this.method = method
    }

    abstract ContractoClassType getReturnType()
}
