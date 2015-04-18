package contracto.model.reflect

import groovy.transform.CompileStatic
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.Parameter

@CompileStatic
class ReflectHelper {
    static ClassNode paramType(MethodNode methodNode, int index) {
        Parameter[] parameters = methodNode.parameters
        Parameter parameter = parameters[index]
        return parameter.type
    }
}
