package contracto.discovery

import contracto.model.contract.HttpMethod
import contracto.model.reflect.ContractoMethod
import groovy.transform.CompileStatic

@CompileStatic
class ContractoMethodFinder {

    public List<ContractoMethod> findRetrofitMethods(List<Class> apis) {
        apis.collectMany { api ->
            api.declaredMethods.findAll { method ->
                method.declaredAnnotations.any(HttpMethod.&isHttpMethod)
            }.collect { method ->
                new ContractoMethod(method, api)
            }
        }
    }
}
