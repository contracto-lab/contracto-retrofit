package contracto.handler

import contracto.matcher.classitem.ClassItemMatcher
import contracto.model.reflect.ContractoClassType
import groovy.transform.CompileStatic

@CompileStatic
class SpringClassItemMatcher extends ClassItemMatcher {

    @Override
    ContractoClassType.ToClass getClassTypeResolver() {
        return new ToClassImpl()
    }
}
