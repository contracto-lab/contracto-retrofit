package contracto.model.contract

import groovy.transform.Canonical
import groovy.transform.CompileStatic

@Canonical
@CompileStatic
class Item {

    String name
    Type type
    boolean optional
    List<Item> embedded
}
