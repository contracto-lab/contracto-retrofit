package contracto

import groovy.transform.CompileStatic
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.FieldNode
import org.codehaus.groovy.ast.GenericsType
import org.codehaus.groovy.ast.MethodNode

import java.lang.reflect.Field
import java.lang.reflect.Method

@CompileStatic
class ContractoClassType {
    static ContractoClassType fromMethod(Method method, Class inClass) {
        Closure<ClassNode> classNode = ContractoClassType.&classNodeFromMethod.curry(method, inClass)
        return fromClass(method.returnType, classNode)
    }

    private static ClassNode classNodeFromMethod(Method method, Class inClass) {
        ClassNode classNode = new ClassNode(inClass)
        MethodNode methodNode = classNode.methods.find { it.name == method.name }
        return methodNode.returnType
    }

    static ContractoClassType fromField(Field field, Class inClass) {
        Closure<ClassNode> classNode = ContractoClassType.&classNodeFromField.curry(field, inClass)
        return fromClass(field.type, classNode)
    }

    private static ClassNode classNodeFromField(Field field, Class inClass) {
        ClassNode classNode = new ClassNode(inClass)
        FieldNode fieldNode = classNode.fields.find { it.name == field.name }
        return fieldNode.type
    }

    private static ContractoClassType fromClass(Class<?> type, Closure<ClassNode> typeNode) {
        if (type == List) {
            ClassNode classNode = typeNode.call()
            Class genericType = extractGenericType(classNode)
            return new ContractoClassType(type: type, genericType: genericType)
        } else {
            return new ContractoClassType(type: type)
        }
    }

    private static Class extractGenericType(ClassNode returnType) {
        GenericsType genericsType = returnType.genericsTypes[0]
        return genericsType.type.getTypeClass()
    }

    Class type
    Class genericType

    ContractoClassType findDeclaredField(String name) {
        Field field = type.declaredFields.find { it.name == name }
        return field ? fromField(field, type) : null
    }
}
