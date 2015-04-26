package contracto.model.contract

import groovy.transform.Canonical
import groovy.transform.CompileStatic

@Canonical
@CompileStatic
class SchemaRequest {

    String path
    String method
    List<Item> headers
    List<Item> params
    Item body
}
