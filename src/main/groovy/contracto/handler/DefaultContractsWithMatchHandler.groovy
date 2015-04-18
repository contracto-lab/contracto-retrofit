package contracto.handler

import contracto.model.ContractMethodMatch
import contracto.model.contract.Item
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
        if (item.type.simple) {
            return checkSimpleTypeMatch(classType, item)
        } else if (item.type.object) {
            return checkObjectTypeMatch(classType, item)
        } else if (item.type.array) {
            return checkArrayTypeMatch(classType, item)
        } else {
            throw new IllegalArgumentException("You shall not pass!")
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
        return item.type.possibleClasses.any { it.isAssignableFrom(classType.type) }
    }

    private boolean checkArrayTypeMatch(ContractoClassType classType, Item item) {
        return classType.type == List && checkClassMatchItem(classType.genericContractoType, item.embedded.first())
    }
}
