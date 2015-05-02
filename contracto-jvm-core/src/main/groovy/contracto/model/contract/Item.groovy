package contracto.model.contract

import groovy.transform.Canonical
import groovy.transform.CompileStatic
import groovy.transform.ToString

@Canonical
@CompileStatic
@ToString(includeNames = true, ignoreNulls = true, includePackage = false)
class Item {

    String name
    JsonType type
    boolean optional
    List<Item> embedded

}
