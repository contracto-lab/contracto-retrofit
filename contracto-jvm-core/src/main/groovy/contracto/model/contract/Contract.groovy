package contracto.model.contract

import groovy.transform.Canonical
import groovy.transform.CompileStatic

import java.lang.reflect.Method

@Canonical
@CompileStatic
class Contract {

    Schema schema
    List<Example> examples

    String getRequestMethod() {
        schema.request.method
    }

    String getRequestPath() {
        schema.request.path
    }

    Item getResponseBody() {
        schema.response.body
    }

    Item getRequestBody() {
        schema.request.body
    }
}
