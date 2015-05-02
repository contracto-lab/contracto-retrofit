package contracto.discovery

import groovy.transform.CompileStatic
import groovy.transform.TupleConstructor

import java.lang.reflect.Method

@CompileStatic
@TupleConstructor
class ContractoMethodFinder {

    final AnnotatedMethodsFinder annotatedMethodsFinder

    List<Method> findMethods(List<Class> classes) {
        classes.collectMany(this.&allMethods)
                .findAll(this.&annotated)
    }

    private List<Method> allMethods(Class aClass) {
        return aClass.declaredMethods as List
    }

    private boolean annotated(Method method) {
        return annotatedMethodsFinder.isAnnotated(method)
    }

    static interface AnnotatedMethodsFinder{
        boolean isAnnotated(Method method)
    }
}
