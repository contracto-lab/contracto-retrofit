package contracto.discovery

import contracto.model.contract.HttpMethod
import contracto.model.reflect.ContractoMethod
import groovy.transform.CompileStatic

@CompileStatic
class ContractoMethodFinder {

    public List<ContractoMethod> findRetrofitMethods(List<Class> apis) {
        apis.collectMany { api ->
            api.declaredMethods.findAll {
                it.declaredAnnotations*.annotationType().any { it in HttpMethod.types }
            }.collect {
                new ContractoMethod(it, api)
            }
        }
    }
}
