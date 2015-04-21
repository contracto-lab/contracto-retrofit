package contracto.model.contract

import retrofit.http.*

import java.lang.annotation.Annotation
import java.lang.reflect.Method

enum HttpMethod {

    get(GET),
    post(POST),
    put(PUT),
    delete(DELETE),
    patch(PATCH),
    head(HEAD)

    private final Class<? extends Annotation> type

    HttpMethod(Class<? extends Annotation> type) {
        this.type = type
    }

    static HttpMethod of(Method method) {
        def types = method.declaredAnnotations*.annotationType()
        return values().find { httpMethod ->
            types.contains(httpMethod.type)
        }
    }

    static boolean isHttpMethod(Annotation annotation) {
        return values()*.type.contains(annotation.annotationType())
    }
}
