package contracto.model.reflect

import groovy.transform.CompileStatic
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.FieldNode
import org.codehaus.groovy.ast.GenericsType
import org.codehaus.groovy.ast.MethodNode
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
        Closure<ClassNode> classNode = ContractoClassType.&classNodeFromField.curry(field)
        return fromClass(field.type, classNode)
    }

    private static ClassNode classNodeFromField(Field field) {
        ClassNode classNode = new ClassNode(field.declaringClass)
        FieldNode fieldNode = classNode.fields.find { it.name == field.name }
        return fieldNode.type
    }

    static ContractoClassType fromParameter(Method method, int parameterIndex) {
        Closure<ClassNode> classNode = ContractoClassType.&classNodeFromParameter.curry(method, parameterIndex)
        return fromClass(method.parameterTypes[parameterIndex], classNode)
    }

    private static ClassNode classNodeFromParameter(Method method, int index) {
        ClassNode classNode = new ClassNode(method.declaringClass)
        MethodNode methodNode = classNode.methods.find { it.name == method.name }
        return methodNode.parameters[index].type
    }

    private static ContractoClassType fromClass(Type type) {
        return new ContractoClassType(type: type)
    }

    private static ContractoClassType fromClass(Class<?> type, Closure<ClassNode> classNodeClosure) {
        if (type == List) {
            ClassNode classNode = classNodeClosure.call()
            Class genericType = extractGenericType(classNode)
            return new ContractoClassType(type: type, genericType: genericType)
        } else {
            return new ContractoClassType(type: type)
        }
    }

    private static Class extractGenericType(ClassNode classNode) {
        GenericsType genericsType = classNode.genericsTypes[0]
        return genericsType.type.getTypeClass()
    }

    Type type
    Class genericType

    ContractoClassType getGenericContractoType() {
        return new ContractoClassType(type: genericType)
    }

    ContractoClassType findDeclaredField(String name) {
        Field field = toClass().declaredFields.find { it.name == name }
        return field ? fromField(field) : null
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
