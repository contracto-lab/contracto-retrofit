package contracto.model

import contracto.model.reflect.ContractoClassType
import contracto.model.reflect.ContractoMethod
import groovy.transform.CompileStatic

import java.lang.reflect.Method

@CompileStatic
class RetrofitContractoMethod extends ContractoMethod {
    RetrofitContractoMethod(Method method) {
        super(method)
    }

    @Override
    ContractoClassType getReturnType() {
        return ContractoClassType.fromMethod(method)
    }
}
