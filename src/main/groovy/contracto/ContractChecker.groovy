package contracto

import groovy.transform.CompileStatic

@CompileStatic
class ContractChecker {

    static boolean checkObjectMatchBody(Class aClass, Item body) {
        return body.embedded.first().name == aClass.declaredFields.first().name
    }

    static boolean checkClassMatchItem(Class aClass, Item item) {
        return aClass in item.type.possibleClasses
    }
}
