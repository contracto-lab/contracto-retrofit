package contracto.discovery

import contracto.model.HttpMethod
import groovy.transform.CompileStatic

import java.lang.reflect.Method

@CompileStatic
class RetrofitAnnotatedMethodsFinder implements ContractoMethodFinder.AnnotatedMethodsFinder {
    @Override
    boolean isAnnotated(Method method) {
        return method.declaredAnnotations.any(HttpMethod.&isHttpMethod)
    }
}
