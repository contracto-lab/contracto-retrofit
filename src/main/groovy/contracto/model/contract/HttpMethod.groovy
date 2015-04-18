package contracto.model.contract

import retrofit.http.DELETE
import retrofit.http.GET
import retrofit.http.POST
import retrofit.http.PUT

import java.lang.annotation.Annotation
import java.lang.reflect.Method

enum HttpMethod {

    get(GET),
    post(POST),
    put(PUT),
    delete(DELETE)

    private final Class<? extends Annotation> type

    HttpMethod(Class<Annotation> type) {
        this.type = type
    }

    static HttpMethod of(Method method) {
        def types = method.declaredAnnotations*.annotationType()
        return values().find { httpMethod ->
            httpMethod.type in types
        }
    }

    static List<Class<? extends Annotation>> getTypes() {
        return values()*.type
    }
}
