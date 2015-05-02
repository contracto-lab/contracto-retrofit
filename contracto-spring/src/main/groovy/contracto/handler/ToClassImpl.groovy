package contracto.handler

import contracto.model.reflect.ContractoClassType
import groovy.transform.CompileStatic
import org.springframework.http.ResponseEntity

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

@CompileStatic
class ToClassImpl implements ContractoClassType.ToClass {

    Class toClass(ContractoClassType classType) {
        if (classType.type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) classType.type
            if (parameterizedType.rawType == ResponseEntity) {
                return (Class) parameterizedType.actualTypeArguments[0]
            }
        }
        return (Class) classType.type
    }

    @Override
    Type toType(ContractoClassType classType) {
        if (classType.type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) classType.type
            if (parameterizedType.rawType == ResponseEntity) {
                return parameterizedType.actualTypeArguments[0]
            }
        }
        return classType.type
    }
}
