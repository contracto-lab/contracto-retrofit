package contracto.matcher.classitem

import contracto.model.MatchError
import contracto.model.contract.Item
import contracto.model.reflect.ContractoClassType
import groovy.transform.CompileStatic

@CompileStatic
class ObjectClassItemMatcher {

    boolean failOnNotImplementedFields = true

    List<MatchError> checkObjectTypeMatch(ContractoClassType classType, Item item, ClassItemMatcher classItemMatcher) {
        if (failOnNotImplementedFields) {
            if (!missingFields(classType, item, classItemMatcher).isEmpty())
                return [new MatchError(classType, item)]
        }
        return existingFields(classType, item, classItemMatcher).collectMany {
            classItemMatcher.checkClassMatchItem(classType.findDeclaredField(it.name, classItemMatcher.classTypeResolver), it)
        }
    }

    private List<Item> missingFields(ContractoClassType classType, Item item, ClassItemMatcher classItemMatcher) {
        return item.embedded.findAll { !existsInClass(classType, it, classItemMatcher) }
    }

    private List<Item> existingFields(ContractoClassType classType, Item item, ClassItemMatcher classItemMatcher) {
        return item.embedded.findAll { existsInClass(classType, it, classItemMatcher) }
    }

    private boolean existsInClass(ContractoClassType classType, Item item, ClassItemMatcher classItemMatcher) {
        return classType.findDeclaredField(item.name, classItemMatcher.classTypeResolver) != null
    }
}
