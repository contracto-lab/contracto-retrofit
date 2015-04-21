package contracto.model

import contracto.model.reflect.ContractoClassType
import spock.lang.Specification

import java.lang.reflect.Field
import java.lang.reflect.Method

import static ContractoClassType.fromField
import static ContractoClassType.fromMethod

class ContractoClassTypeSpec extends Specification {

    static List<Integer> justMethod() {
        return null
    }

    List<String> justField

    def "From method will create type with generic type equals integer"() {
        given:
        Method method = ContractoClassTypeSpec.getDeclaredMethod('justMethod')
        when:
        ContractoClassType listType = fromMethod(method)
        then:
        listType.genericType == Integer
    }

    def "From field will create type with generic type equals integer"() {
        given:
        Field field = ContractoClassTypeSpec.getDeclaredField('justField')
        when:
        ContractoClassType listType = fromField(field)
        then:
        listType.genericType == String
    }
}
