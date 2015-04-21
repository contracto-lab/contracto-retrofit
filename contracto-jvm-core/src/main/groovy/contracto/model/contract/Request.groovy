package contracto.model.contract

import groovy.transform.Canonical
import groovy.transform.CompileStatic

@Canonical
@CompileStatic
class Request {

    String httpMethod
    String path
    Meta meta
}
