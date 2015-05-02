package contracto.matcher.classitem

import contracto.model.contract.Item
import contracto.model.error.FieldMatchError
import contracto.model.error.MatchError
import contracto.model.reflect.ContractoClassType
import groovy.transform.CompileStatic

@CompileStatic
class SimpleClassItemMatcher {
    List<MatchError> checkSimpleTypeMatch(ContractoClassType classType, Item item, ClassItemMatcher classItemMatcher) {
        if (item.type.possibleClasses.any { it.isAssignableFrom((Class) classType.type) })
            return []
        return [new FieldMatchError(classType, item)]
    }
}
