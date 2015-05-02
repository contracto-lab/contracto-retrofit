package contracto.discovery

import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.RequestMapping

import java.lang.reflect.Method

@CompileStatic
class SpringAnnotatedMethodsFinder  implements ContractoMethodFinder.AnnotatedMethodsFinder {
    @Override
    boolean isAnnotated(Method method) {
        return method.getAnnotation(RequestMapping)
    }
}
