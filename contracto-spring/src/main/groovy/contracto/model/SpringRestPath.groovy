package contracto.model

import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.RequestMapping

import java.lang.reflect.Method

@CompileStatic
class SpringRestPath {

    private Collection<String> value

    static SpringRestPath from(Method method) {
        return new SpringRestPath(value: path(method))
    }

    private static Collection<String> path(Method method) {
        def classPath = method.declaringClass.getAnnotation(RequestMapping)?.value() ?: []
        def methodPath = method.getAnnotation(RequestMapping).value()

        Collection<Collection<String>> permutation = [classPath, methodPath].combinations()

        if (permutation.size() > 0) {
            return permutation*.join('')
        } else {
            return [classPath, methodPath].flatten() as Collection<String>
        }
    }

    boolean matches(String path) {
        return this.value.contains(path)
    }

}
