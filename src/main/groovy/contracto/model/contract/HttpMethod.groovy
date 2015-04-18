package contracto.model.contract

import retrofit.http.*

import java.lang.annotation.Annotation
import java.lang.reflect.Method

enum HttpMethod {

    get(GET),
    post(POST),
    put(PUT),
    delete(DELETE),
    path(PATCH),
    head(HEAD)

    private final Class<? extends Annotation> type

    HttpMethod(Class<? extends Annotation> type) {
        this.type = type
    }

    static HttpMethod of(Method method) {
        def types = method.declaredAnnotations*.annotationType()
        return values().find { httpMethod ->
            httpMethod.type in types
        }
    }

    static boolean isHttpMethod(Annotation annotation) {
        return annotation.annotationType() in values()*.type
    }
}
