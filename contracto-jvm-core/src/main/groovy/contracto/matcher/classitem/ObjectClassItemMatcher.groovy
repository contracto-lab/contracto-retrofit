package contracto.matcher.classitem

import contracto.model.contract.Item
import contracto.model.error.FieldMissingMatchError
import contracto.model.error.MatchError
import contracto.model.reflect.ContractoClassType
import groovy.transform.CompileStatic

@CompileStatic
class ObjectClassItemMatcher {

    boolean failOnNotImplementedFields = true

    List<MatchError> checkObjectTypeMatch(ContractoClassType classType, Item item, ClassItemMatcher classItemMatcher) {
        if (failOnNotImplementedFields) {
            List<Item> fields = missingFields(classType, item, classItemMatcher)
            if (!fields.isEmpty()) {
                return fields.collect { new FieldMissingMatchError(classType, it) }
            }
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
