package contracto.model.contract

import retrofit.http.DELETE
import retrofit.http.GET
import retrofit.http.POST
import retrofit.http.PUT

import java.lang.annotation.Annotation

enum HttpMethod {

    get(GET),
    post(POST),
    put(PUT),
    delete(DELETE)

    private Class<Annotation> annotation

    HttpMethod(Class<Annotation> annotation) {
        this.annotation = annotation
    }

    static HttpMethod basedOnAnnotation(Annotation annotation) {
        for (HttpMethod httpMethod : values()) {
            if (httpMethod.annotation == annotation.annotationType())
                return httpMethod
        }
        throw new RuntimeException("Annotation ${annotation.annotationType().name} does not match any HttpMethod")
    }
}
