package contracto.discovery

import contracto.model.HttpMethod
import contracto.model.reflect.ContractoMethod
import groovy.transform.CompileStatic

@CompileStatic
class ContractoMethodFinder {

    public List<ContractoMethod> findRetrofitMethods(List<Class> apis) {
        apis.collectMany {
            it.declaredMethods.findAll {
                it.declaredAnnotations.any(HttpMethod.&isHttpMethod)
            }.collect {
                new ContractoMethod(it)
            }
        }
    }
}
