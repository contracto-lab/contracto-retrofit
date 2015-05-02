package contracto.model.contract

import groovy.transform.Canonical
import groovy.transform.CompileStatic
import groovy.transform.ToString

@Canonical
@CompileStatic
@ToString(ignoreNulls = true, includePackage = false, includeNames = true)
class Schema {

    SchemaRequest request
    SchemaResponse response
}
