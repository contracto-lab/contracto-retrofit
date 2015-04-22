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

        List<String> paths = elements.collect { it.getAnnotation(RequestMapping) }.findAll { it != null }.collect {
            return it.value().size() > 0 ? it.value()[0] : ""
        }
        return paths.join("")
    }


    boolean matches(String path) {
        return this.value == path
    }


}
