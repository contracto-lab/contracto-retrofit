package contracto

import groovy.transform.Canonical

@Canonical
class Request {

    HttpMethod httpMethod
    String path
    Meta meta
}
