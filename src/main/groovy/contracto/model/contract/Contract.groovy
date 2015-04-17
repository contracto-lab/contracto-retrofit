package contracto.model.contract

import contracto.discovery.RetrofitMethodsFinder
import groovy.transform.Canonical
import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic

import java.lang.annotation.Annotation
import java.lang.reflect.Method

@Canonical
@CompileStatic
class Contract {

    Request request

    boolean isMatching(Method method) {
        return httpMethod(method) == request.httpMethod && path(method) == request.path
    }

    HttpMethod httpMethod(Method method) {
        return HttpMethod.get
    }

    @CompileDynamic
    String path(Method method) {
        Annotation get = method.declaredAnnotations.find {
            it.annotationType() in RetrofitMethodsFinder.retrofitAnnotations
        }
        return get.value()
    }

    void displayWarning() {
        System.err.println("Warning no matching for: \n$this")
    }
}
