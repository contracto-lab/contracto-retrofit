package contracto.handler

import contracto.handler.matcher.ClassItemMatcher
import contracto.model.reflect.ContractoClassType
import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.RequestBody

import java.lang.annotation.Annotation

@CompileStatic
class SpringClassItemMatcher extends ClassItemMatcher {
    @Override
    protected boolean withBody(Annotation[] annotations) {
        return annotations*.annotationType().contains(RequestBody)
    }

    @Override
    protected ContractoClassType.ToClass getClassTypeResolver() {
        return new ToClassImpl()
    }
}
