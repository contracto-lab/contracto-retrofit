package contracto

import groovy.transform.Canonical

@Canonical
class MetaRequest {

    List<Item> pathParams
    List<Item> queryParams
    List<Item> headers
    Item body
}
