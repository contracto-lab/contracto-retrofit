package contracto.handler

import contracto.model.ContractMethodMatch
import contracto.model.contract.Item
import contracto.model.contract.Type
import contracto.model.reflect.ContractoClassType
import contracto.model.reflect.ContractoMethod
import groovy.transform.CompileStatic

@CompileStatic
class DefaultContractsWithMatchHandler {
    boolean handle(List<ContractMethodMatch> matches) {
        return matches.every { ContractMethodMatch it->
            ContractoMethod method = it.method
            Item item = it.contract.request.meta.response.body
            checkClassMatchItem(method.returnType, item)
        }
    }
    boolean checkClassMatchItem(ContractoClassType classType, Item item) {
        if (item.type.isSimpleType) {
            return classType.type in item.type.possibleClasses
        } else if (item.type == Type.object) {
            return item.embedded.every {
                checkItemExistsInClass(classType, it)
            }
        } else {
            return classType.type == List && checkClassMatchItem(new ContractoClassType(type: classType.genericType), item.embedded.first())
        }
    }

    private boolean checkItemExistsInClass(ContractoClassType classType, Item item) {
        def find = classType.findDeclaredField(item.name)
        return find ? checkClassMatchItem(find, item) : false
    }
}
