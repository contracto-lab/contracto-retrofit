package contracto.model.contract

import groovy.transform.Canonical
import groovy.transform.CompileStatic

import java.lang.reflect.Method

@Canonical
@CompileStatic
class Contract {

    Request request
    Schema schema

    String getRequestMethod() {
        request.httpMethod
    }

    String getRequestPath() {
        request.path
    }

    Item getResponseBody() {
        request.schema.response.body
    }

    Item getRequestBody() {
        request.schema.request.body
    }
}
