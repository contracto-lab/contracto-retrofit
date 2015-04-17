package contracto

import groovy.transform.Canonical

@Canonical
class Item {

    String name
    Type type
    boolean optional
    List<Item> embedded
}
