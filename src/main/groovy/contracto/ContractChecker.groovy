package contracto

import groovy.transform.CompileStatic

@CompileStatic
class ContractChecker {

    static boolean checkMethodReturnTypeMatchItem(ContractoMethod contractoMethod, Item item) {
        return checkClassMatchItem(contractoMethod.returnType, item)
    }

    static boolean checkClassMatchItem(ContractoClassType classType, Item item) {
        if (item.type.isSimpleType) {
            return classType.type in item.type.possibleClasses
        } else if (item.type == Type.object) {
            return item.embedded.every {
                checkItemExistsInClass(classType, it)
            }
        } else {
            throw new UnsupportedOperationException('It is hard to tell when it is array...')
        }
    }

    static boolean checkItemExistsInClass(ContractoClassType classType, Item item) {
        def find = classType.type.declaredFields.find { it.name == item.name }
        return find ? checkClassMatchItem(new ContractoClassType(type: find.type), item) : false
    }
}
