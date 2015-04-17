package contracto

import contracto.api.ContractoService
import groovy.transform.CompileStatic

import java.lang.reflect.Method

@CompileStatic
class Contracto {
    static boolean checkContracts(List<Class> apis, List<String> urls) {
        List<Contract> contracts = new ContractoService().call(urls)
        List<Method> methods = methods(apis)
        ContractMatcher matcher = new ContractMatcher(methods, contracts)
        matcher.findContractsWithoutMatch()*.displayWarning()
        return matcher.findMatching().every{it.match()}
    }

    private static List<Method> methods(List<Class> apis) {
        List<Method> methods = []
        apis.each {
            methods.addAll(it.methods)
        }
        return methods
    }
}
