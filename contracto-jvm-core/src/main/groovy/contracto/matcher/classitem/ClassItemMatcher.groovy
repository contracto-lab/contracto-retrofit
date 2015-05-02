package contracto.matcher.classitem

import contracto.model.MatchError
import contracto.model.contract.Item
import contracto.model.reflect.ContractoClassType
import groovy.transform.CompileStatic

@CompileStatic
abstract class ClassItemMatcher {

    SimpleClassItemMatcher simpleClassItemMatcher = new SimpleClassItemMatcher()
    ObjectClassItemMatcher objectClassItemMatcher = new ObjectClassItemMatcher()
    ArrayClassItemMatcher arrayClassItemMatcher = new ArrayClassItemMatcher()

    List<MatchError> checkClassMatchItem(ContractoClassType classType, Item item) {
        if (item.type.simple) {
            return simpleClassItemMatcher.checkSimpleTypeMatch(classType, item, this)
        } else if (item.type.object) {
            return objectClassItemMatcher.checkObjectTypeMatch(classType, item, this)
        } else if (item.type.array) {
            return arrayClassItemMatcher.checkArrayTypeMatch(classType, item, this)
        } else {
            throw new IllegalArgumentException("You shall not pass!")
        }
    }

    //TODO: move to ObjectClassItemMatcher
    abstract ContractoClassType.ToClass getClassTypeResolver()
}
