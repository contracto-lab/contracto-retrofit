package contracto.model

import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.RequestMapping

import java.lang.reflect.AnnotatedElement
import java.lang.reflect.Method

@CompileStatic
class SpringRestPath {

    private Collection<String> value

    static SpringRestPath from(Method method) {
        return new SpringRestPath(value: path(method))
    }

    private static Collection<String> path(Method method) {
        Collection<String> classPath = annotationValue(method.declaringClass)
        Collection<String> methodPath = annotationValue(method)

        return combinePaths(classPath, methodPath)
    }

    private static Collection<String> combinePaths(Collection<String> classPath, Collection<String> methodPath) {
        if( classPath.size() == 0){
            return methodPath
        }
        if( methodPath.size() == 0){
            return classPath
        }

        Collection<Collection<String>> permutation = [classPath, methodPath].combinations()
        return permutation*.join('')
    }

    private static Collection<String> annotationValue(AnnotatedElement element){
        return element.getAnnotation(RequestMapping)?.value() as Collection<String> ?: []
    }

    boolean matches(String path) {
        return this.value.contains(path)
    }

}
