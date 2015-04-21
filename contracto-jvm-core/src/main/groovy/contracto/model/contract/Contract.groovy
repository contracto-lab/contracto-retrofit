package contracto.model.contract

import groovy.transform.Canonical
import groovy.transform.CompileStatic

import java.lang.reflect.Method

@Canonical
@CompileStatic
class Contract {

    Request request

    boolean isMatching(Method method) {
        return HttpMethod.of(method) == request.httpMethod &&
                RetrofitPath.from(method).matches(request.path)
    }

    void displayWarning() {
        System.err.println("Warning no matching for: \n$this")
    }
}
