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
        return method.getAnnotation(RequestMapping).value()[0]
    }

    boolean matches(String path) {
        return this.value == path
    }


}
