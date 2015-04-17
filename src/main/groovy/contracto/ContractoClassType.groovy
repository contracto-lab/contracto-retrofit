package contracto

import groovy.transform.CompileStatic

import java.lang.reflect.Field
import java.lang.reflect.Method

@CompileStatic
class ContractoClassType {
    static ContractoClassType fromMethod(Method method, Class inClass) {
        if(method.returnType instanceof List){
            //TODO make funny logic here
            throw new UnsupportedOperationException('TODO make some List magic')
        }else{
            return new ContractoClassType(type: method.returnType)
        }
    }
    static ContractoClassType fromField(Field field, Class inClass) {
        if(field.type instanceof List){
            //TODO make funny logic here
            throw new UnsupportedOperationException('TODO make some List magic')
        }else{
            return new ContractoClassType(type: field.type)
        }
    }
    Class type
    Class genericType

    ContractoClassType findDeclaredField(String name) {
        Field field = type.declaredFields.find { it.name == name }
        return field ? fromField(field, type) : null
    }
}
