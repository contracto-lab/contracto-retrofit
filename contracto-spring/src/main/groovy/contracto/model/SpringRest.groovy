package contracto.model

import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.RequestMapping

import java.lang.reflect.Method

@CompileStatic
class SpringRest {

    private String value

    static SpringRest from(Method method) {
        return new SpringRest(value: path(method))
    }

    private static String path(Method method) {
        def elements = [method.declaringClass, method]

        Collection<RequestMapping> annotations = elements*.getAnnotation(RequestMapping).findAll()

        return annotations.collect { annotation ->
            value(annotation)
        }.join("")
    }

    boolean matches(String path) {
        return this.value == path
    }

    private static String value(RequestMapping element) {
        return element.value().size() > 0 ? element.value()[0] : ""
    }


}
