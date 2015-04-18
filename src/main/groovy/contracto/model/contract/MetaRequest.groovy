package contracto.model.contract

import groovy.transform.Canonical
import groovy.transform.CompileStatic

@Canonical
@CompileStatic
class MetaRequest {

    List<Item> pathParams
    List<Item> queryParams
    List<Item> headers
    Item body
}
