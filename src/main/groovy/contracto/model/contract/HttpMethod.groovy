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

    final Class<? extends Annotation> annotation

    HttpMethod(Class<Annotation> annotation) {
        this.annotation = annotation
    }

    static HttpMethod of(Method method) {
        def types = method.declaredAnnotations*.annotationType()
        return values().find { httpMethod ->
            httpMethod.annotation in types
        }
    }
}
