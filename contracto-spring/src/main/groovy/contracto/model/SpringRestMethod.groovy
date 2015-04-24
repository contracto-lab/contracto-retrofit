package contracto.model

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import java.lang.reflect.AnnotatedElement
import java.lang.reflect.Method

class SpringRestMethod {

    private Collection<RequestMethod> value

    static SpringRestMethod from(Method method) {
        return new SpringRestMethod(value: extractMethod(method))
    }

    private static Collection<RequestMethod> extractMethod(Method method) {
        Collection<RequestMethod> classHttpMethod = annotationMethod(method.declaringClass)
        Collection<RequestMethod> methodHttpMethod = annotationMethod(method)

        return allHttpMethods(classHttpMethod, methodHttpMethod)
    }

    static Collection<RequestMethod> allHttpMethods(Collection<RequestMethod> classHttpMethod, Collection<RequestMethod> methodHttpMethod) {
        Set<RequestMethod> methods = new HashSet<>()
        methods.addAll(classHttpMethod)
        methods.addAll(methodHttpMethod)

        return methods.size() > 0 ? methods : [RequestMethod.GET]
    }

    private static Collection<RequestMethod> annotationMethod(AnnotatedElement element){
        return element.getAnnotation(RequestMapping)?.method() ?: [] as Collection<RequestMethod>
    }

    boolean matches(String method) {
        return this.value.contains(RequestMethod.valueOf(method.toUpperCase()))
    }

}
