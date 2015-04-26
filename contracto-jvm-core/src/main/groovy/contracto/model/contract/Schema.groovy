package contracto.model.contract

import groovy.transform.Canonical
import groovy.transform.CompileStatic

@Canonical
@CompileStatic
class Schema {

    SchemaRequest request
    SchemaResponse response
}
