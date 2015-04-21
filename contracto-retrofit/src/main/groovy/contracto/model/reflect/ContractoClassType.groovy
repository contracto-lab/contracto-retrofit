package contracto.model.reflect

import com.google.gson.FieldNamingPolicy
import com.google.gson.annotations.SerializedName
import groovy.transform.CompileStatic
import rx.Observable

import java.lang.reflect.Field
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

@CompileStatic
class ContractoClassType {

    static ContractoClassType fromMethod(Method method) {
        return fromClass(method.genericReturnType)
    }

    static ContractoClassType fromField(Field field) {
        return fromClass(field.genericType)
    }

    static ContractoClassType fromParameter(Method method, int parameterIndex) {
        return fromClass(method.genericParameterTypes[parameterIndex])
    }

    private static ContractoClassType fromClass(Type type) {
        return new ContractoClassType(type: type)
    }

    Type type

    ContractoClassType findDeclaredField(String name) {
        Field field = toClass().declaredFields.find { name == jsonNameForField(it) }
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

    private Class toClass() {
        if (this.type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type
            if (parameterizedType.rawType == Observable) {
                return (Class) parameterizedType.actualTypeArguments[0]
            }
        }
        return (Class) this.type
    }
}
