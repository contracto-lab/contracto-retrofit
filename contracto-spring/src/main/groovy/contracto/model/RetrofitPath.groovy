package contracto.model

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic

import java.lang.annotation.Annotation
import java.lang.reflect.Method

@CompileStatic
class RetrofitPath {

    private String value

    static RetrofitPath from(Method method) {
        return new RetrofitPath(value: path(method))
    }

    @CompileDynamic
    private static String path(Method method) {
        Annotation annotation = method.declaredAnnotations.find(HttpMethod.&isHttpMethod)
        return annotation.value()
    }

    boolean matches(String path) {
        return this.value == path
    }
}
