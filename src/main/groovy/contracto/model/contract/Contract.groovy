package contracto.model.contract

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
        return HttpMethod.of(method) == request.httpMethod && path(method) == request.path
    }

    @CompileDynamic
    String path(Method method) {
        Annotation retrofitAnnotation = method.declaredAnnotations.find {
            it.annotationType() in HttpMethod.values()*.annotation
        }
        return retrofitAnnotation.value()
    }

    void displayWarning() {
        System.err.println("Warning no matching for: \n$this")
    }
}
