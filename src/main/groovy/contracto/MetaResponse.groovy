package contracto

import groovy.transform.Canonical

@Canonical
class MetaResponse {

    List<Item> headers
    Item body
}
