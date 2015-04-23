package contracto.discovery

import contracto.model.reflect.ContractoMethod
import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.RequestMapping

import java.lang.reflect.Method

@CompileStatic
class ContractoMethodFinder {

    List<ContractoMethod> findMethods(List<Class> controllers) {

        controllers.collectMany { Class controller ->
            extractMethods(controller).collect {
                new ContractoMethod(it)
            }
        }
    }

    private Collection<Method> extractMethods(Class controller) {
        if (controller.getAnnotation(RequestMapping)) {
            return controller.declaredMethods.findAll {
                !it.isSynthetic()
            }
        } else {
            return controller.declaredMethods.findAll {
                it.getAnnotation(RequestMapping) ? true : false
            }
        }
    }
}
