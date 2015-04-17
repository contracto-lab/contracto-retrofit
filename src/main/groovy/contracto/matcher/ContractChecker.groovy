package contracto.matcher

import contracto.model.reflect.ContractoClassType
import contracto.model.reflect.ContractoMethod
import contracto.model.contract.Item
import contracto.model.contract.Type
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
            return classType.type == List && checkClassMatchItem(new ContractoClassType(type: classType.genericType),item.embedded.first())
        }
    }

    static boolean checkItemExistsInClass(ContractoClassType classType, Item item) {
        def find = classType.findDeclaredField(item.name)
        return find ? checkClassMatchItem(find, item) : false
    }
}
