package contracto.discovery

import contracto.model.reflect.ContractoMethod
import groovy.transform.CompileStatic

@CompileStatic
class ContractoMethodFinder {


    public List<ContractoMethod> findMethods(List<Class> apis) {
        List<ContractoMethod> methods = []
        for(Class api: apis){
            for (int i = 0; i < api.methods.length; i++) {
                methods.add(new ContractoMethod(api.methods[i], api))
            }
        }
        return methods
    }
}
