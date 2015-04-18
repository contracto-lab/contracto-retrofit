package contracto.handler

import contracto.model.ContractMethodMatch
import contracto.model.contract.Item
import contracto.model.contract.Type
import contracto.model.reflect.ContractoClassType
import groovy.transform.CompileStatic

@CompileStatic
class DefaultContractsWithMatchHandler {

    boolean failOnNotImplementedFields = true

    boolean handle(List<ContractMethodMatch> matches) {
        return matches.every { ContractMethodMatch it ->
            ContractoClassType returnType = it.method.returnType
            Item item = it.contract.request.meta.response.body
            checkClassMatchItem(returnType, item)
        }
    }

    boolean checkClassMatchItem(ContractoClassType classType, Item item) {
        if (item.type.isSimpleType) {
            return checkSimpleTypeMatch(classType, item)
        } else if (item.type == Type.object) {
            return checkObjectTypeMatch(classType, item)
        } else {
            return checkArrayTypeMatch(classType, item)
        }
    }

    private boolean checkObjectTypeMatch(ContractoClassType classType, Item item) {
        if (failOnNotImplementedFields) {
            if (!missingFields(classType, item).isEmpty())
                return false
        }
        return existingFields(classType, item).every {
            checkClassMatchItem(classType.findDeclaredField(it.name), it)
        }
    }

    private List<Item> existingFields(ContractoClassType classType, Item item) {
        return item.embedded.findAll { existsInClass(classType, it) }
    }

    private List<Item> missingFields(ContractoClassType classType, Item item) {
        return item.embedded.findAll { !existsInClass(classType, it) }
    }

    private boolean existsInClass(ContractoClassType classType, Item item) {
        return classType.findDeclaredField(item.name) != null
    }

    private boolean checkSimpleTypeMatch(ContractoClassType classType, Item item) {
        return classType.type in item.type.possibleClasses
    }

    private boolean checkArrayTypeMatch(ContractoClassType classType, Item item) {
        return classType.type == List && checkClassMatchItem(classType.genericContractoType, item.embedded.first())
    }
}
