package contracto

import groovy.transform.CompileStatic

import java.lang.reflect.Method

@CompileStatic
class ContractoClassType {
    static ContractoClassType from(Method method, Class inClass) {
        if(method.returnType instanceof List){
            //TODO make funny logic here
            throw new UnsupportedOperationException('TODO make some List magic')
        }else{
            return new ContractoClassType(type: method.returnType)
        }
    }
    Class type
    Class genericType
}
