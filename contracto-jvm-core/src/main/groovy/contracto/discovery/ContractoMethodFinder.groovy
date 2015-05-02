package contracto.discovery

import contracto.model.reflect.ContractoMethod
import groovy.transform.CompileStatic
import groovy.transform.TupleConstructor

import java.lang.reflect.Method

@CompileStatic
@TupleConstructor
class ContractoMethodFinder {

    final AnnotatedMethodsFinder annotatedMethodsFinder

    List<ContractoMethod> findMethods(List<Class> classes) {
        classes.collectMany(this.&allMethods)
                .findAll(this.&annotated)
                .collect(this.&wrap)
    }

    private List<Method> allMethods(Class aClass) {
        return aClass.declaredMethods as List
    }

    private boolean annotated(Method method) {
        return annotatedMethodsFinder.isAnnotated(method)
    }

    private ContractoMethod wrap(Method method) {
        return new ContractoMethod(method)
    }

    static interface AnnotatedMethodsFinder{
        boolean isAnnotated(Method method)
    }
}
