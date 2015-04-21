package contracto.handler

import contracto.model.reflect.ContractoClassType
import groovy.transform.CompileStatic
import rx.Observable

import java.lang.reflect.ParameterizedType

@CompileStatic
class ToClassImpl implements ContractoClassType.ToClass{

    Class toClass(ContractoClassType classType) {
        if (classType.type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) classType.type
            if (parameterizedType.rawType == Observable) {
                return (Class) parameterizedType.actualTypeArguments[0]
            }
        }
        return (Class) classType.type
    }
}
