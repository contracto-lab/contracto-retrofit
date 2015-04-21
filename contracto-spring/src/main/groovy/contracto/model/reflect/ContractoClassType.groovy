package contracto.model.reflect

import groovy.transform.CompileStatic
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.FieldNode
import org.codehaus.groovy.ast.GenericsType
import org.codehaus.groovy.ast.MethodNode

import java.lang.reflect.Field
import java.lang.reflect.Method
import java.lang.reflect.Parameter

@CompileStatic
class ContractoClassType {

    static ContractoClassType fromMethod(Method method) {
        Closure<ClassNode> classNode = ContractoClassType.&classNodeFromMethod.curry(method)
        return fromClass(method.returnType, classNode)
    }

    private static ClassNode classNodeFromMethod(Method method) {
        ClassNode classNode = new ClassNode(method.declaringClass)
        MethodNode methodNode = classNode.methods.find { it.name == method.name }
        return methodNode.returnType
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

    static ContractoClassType fromParameter(Parameter parameter, int index) {
        Closure<ClassNode> classNode = ContractoClassType.&classNodeFromParameter.curry(parameter, index)
        return fromClass(parameter.type, classNode)
    }

    private static ClassNode classNodeFromParameter(Parameter parameter, int index) {
        ClassNode classNode = new ClassNode(parameter.declaringExecutable.declaringClass)
        MethodNode methodNode = classNode.methods.find { it.name == parameter.declaringExecutable.name }
        org.codehaus.groovy.ast.Parameter paramNode = methodNode.parameters[index]
        return paramNode.type
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
    Class type

    Class genericType

    ContractoClassType getGenericContractoType() {
        return new ContractoClassType(type: genericType)
    }

    ContractoClassType findDeclaredField(String name) {
        Field field = type.declaredFields.find { it.name == name }
        return field ? fromField(field) : null
    }
}