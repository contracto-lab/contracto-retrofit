package contracto.matcher.classitem

import contracto.model.contract.Item
import contracto.model.error.ArrayMatchError
import contracto.model.error.MatchError
import contracto.model.reflect.ContractoClassType
import groovy.transform.CompileStatic

import java.lang.reflect.GenericArrayType
import java.lang.reflect.ParameterizedType

@CompileStatic
class ArrayClassItemMatcher {
    List<MatchError> checkArrayTypeMatch(ContractoClassType contractoClassType, Item item, ClassItemMatcher classItemMatcher) {
        if (contractoClassType.type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) contractoClassType.type
            if (List.isAssignableFrom((Class) parameterizedType.rawType)) {
                def cct = new ContractoClassType(type: parameterizedType.actualTypeArguments[0])
                return classItemMatcher.checkClassMatchItem(cct, item.embedded.first())
            }
        } else if (contractoClassType.type instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType) contractoClassType.type
            def cct = new ContractoClassType(type: genericArrayType.genericComponentType)
            return classItemMatcher.checkClassMatchItem(cct, item.embedded.first())
        }
        return [new ArrayMatchError(contractoClassType, item)]
    }
}
