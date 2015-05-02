package contracto.handler

import groovy.transform.CompileStatic

import java.lang.reflect.Method

@CompileStatic
class DefaultMethodsWithoutMatchHandler {
    boolean failOnMethodsWithoutMatch = false

    boolean handle(List<Method> methods) {
        methods.each{
            System.err.println("Warning no matching for: \n$it")
        }
        return methods.isEmpty() ? true : !failOnMethodsWithoutMatch
    }
}
