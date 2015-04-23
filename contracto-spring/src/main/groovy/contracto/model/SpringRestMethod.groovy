package contracto.model

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import java.lang.reflect.Method

class SpringRestMethod {

    private Collection<RequestMethod> value

    static SpringRestMethod from(Method method) {
        return new SpringRestMethod(value: extractMethod(method))
    }

    private static Collection<RequestMethod> extractMethod(Method method) {
        def classHttpMethod = method.declaringClass.getAnnotation(RequestMapping)?.method() ?: []
        def methodHttpMethod = method.getAnnotation(RequestMapping).method() ?: [RequestMethod.GET]

        Set<RequestMethod> allHttpMethod = new HashSet<>()
        allHttpMethod.addAll(classHttpMethod)
        allHttpMethod.addAll(methodHttpMethod)

        return allHttpMethod
    }

    boolean matches(String method) {
        return this.value.contains(RequestMethod.valueOf(method.toUpperCase()))
    }

    private static String value(RequestMapping element) {
        return element.method().size() > 0 ? element.value()[0] : ""
    }
}
