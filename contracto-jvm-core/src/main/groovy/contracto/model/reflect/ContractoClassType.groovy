package contracto.model.reflect

import com.google.gson.FieldNamingPolicy
import com.google.gson.annotations.SerializedName
import groovy.transform.CompileStatic

import java.lang.reflect.Field
import java.lang.reflect.Method
import java.lang.reflect.Type

@CompileStatic
class ContractoClassType {

    static ContractoClassType fromMethod(Method method) {
        return new ContractoClassType(type: method.genericReturnType)
    }

    static ContractoClassType fromField(Field field) {
        return new ContractoClassType(type: field.genericType)
    }

    static ContractoClassType fromParameter(Method method, int parameterIndex) {
        return new ContractoClassType(type: method.genericParameterTypes[parameterIndex])
    }

    Type type

    ContractoClassType findDeclaredField(String name, ToClass toClass) {
        Field field = toClass.toClass(this).declaredFields.find { name == jsonNameForField(it) }
        return field ? fromField(field) : null
    }

    private String jsonNameForField(Field field) {
        def annotation = field.getAnnotation(SerializedName)
        if (annotation) {
            return annotation.value()
        } else {
            return FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES.translateName(field)
        }
    }

    interface ToClass{
        Class toClass(ContractoClassType contractoClassType)
    }

}
