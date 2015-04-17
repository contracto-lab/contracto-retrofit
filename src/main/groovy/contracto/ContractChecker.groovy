package contracto

import groovy.transform.CompileStatic

@CompileStatic
class ContractChecker {

    static boolean checkClassMatchItem(Class aClass, Item item) {
        if (item.type.isSimpleType) {
            return aClass in item.type.possibleClasses
        } else if (item.type == Type.object) {
            return item.embedded.every {
                checkItemExistsInClass(aClass, it)
            }
        } else {
            throw new UnsupportedOperationException('It is hard to tell when it is array...')
        }
    }

    static boolean checkItemExistsInClass(Class aClass, Item item) {
        def find = aClass.declaredFields.find { it.name == item.name }
        return find ? checkClassMatchItem(find.type, item) : false
    }
}
