package contracto.discovery

import contracto.model.reflect.ContractoMethod
import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.RequestMapping

@CompileStatic
class ContractoMethodFinder {

    List<ContractoMethod> findMethods(List<Class> controllers) {

        controllers.collectMany { Class controller ->
            if (controller.getAnnotation(RequestMapping)) {
                return controller.declaredMethods.findAll {
                    !it.isSynthetic()
                }.collect {
                    new ContractoMethod(it)
                }
            } else {
                return controller.declaredMethods.findAll {
                    it.getAnnotation(RequestMapping) ? true : false
                }.collect {
                    new ContractoMethod(it)
                }
            }
        }
    }
}
