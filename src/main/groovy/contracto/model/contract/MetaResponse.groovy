package contracto.model.contract

import groovy.transform.Canonical
import groovy.transform.CompileStatic

@Canonical
@CompileStatic
class MetaResponse {

    List<Item> headers
    Item body
}
