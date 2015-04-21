package contracto.handler

import contracto.model.reflect.ContractoClassType
import groovy.transform.CompileStatic

@CompileStatic
class ToClassImpl implements ContractoClassType.ToClass {

    Class toClass(ContractoClassType classType) {
        return (Class) classType.type
    }
}
