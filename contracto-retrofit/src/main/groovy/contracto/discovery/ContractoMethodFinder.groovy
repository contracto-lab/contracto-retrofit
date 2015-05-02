package contracto.discovery

import contracto.model.HttpMethod
import contracto.model.reflect.ContractoMethod
import groovy.transform.CompileStatic

import java.lang.reflect.Method

@CompileStatic
class ContractoMethodFinder {

    List<ContractoMethod> findMethods(List<Class> classes) {
        classes.collectMany(this.&allMethods)
                .findAll(this.&annotated)
                .collect(this.&wrap)
    }

    private List<Method> allMethods(Class aClass) {
        return aClass.declaredMethods as List
    }

    private boolean annotated(Method method) {
        return method.declaredAnnotations.any(HttpMethod.&isHttpMethod)
    }

    private ContractoMethod wrap(Method method) {
        return new ContractoMethod(method)
    }
}
