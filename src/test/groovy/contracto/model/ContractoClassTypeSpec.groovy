package contracto.model

import contracto.model.reflect.ContractoClassType
import spock.lang.Specification

import java.lang.reflect.Field
import java.lang.reflect.Method

import static ContractoClassType.fromField
import static ContractoClassType.fromMethod

class ContractoClassTypeSpec extends Specification {

    static List<Integer> justMethod() {
        return null;
    }

    List<String> justField

    def "From method will create type with generic type equals integer"() {
        given:
        Method justAMethod = ContractoClassTypeSpec.declaredMethods.find {
            it.name == 'justMethod'
        }
        when:
        ContractoClassType listType = fromMethod(justAMethod, ContractoClassTypeSpec)
        then:
        listType.genericType == Integer
    }

    def "From field will create type with generic type equals integer"() {
        given:
        Field justAField = ContractoClassTypeSpec.declaredFields.find {
            it.name == 'justField'
        }
        when:
        ContractoClassType listType = fromField(justAField, ContractoClassTypeSpec)
        then:
        listType.genericType == String
    }
}
