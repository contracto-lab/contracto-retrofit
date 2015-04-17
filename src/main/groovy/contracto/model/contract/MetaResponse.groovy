package contracto.model.contract

import groovy.transform.Canonical

@Canonical
class MetaResponse {

    List<Item> headers
    Item body
}
