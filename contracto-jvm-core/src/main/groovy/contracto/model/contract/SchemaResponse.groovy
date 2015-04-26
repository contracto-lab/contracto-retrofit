package contracto.model.contract

import groovy.transform.Canonical
import groovy.transform.CompileStatic

@Canonical
@CompileStatic
class SchemaResponse {

    List<Item> headers
    Item body
}
