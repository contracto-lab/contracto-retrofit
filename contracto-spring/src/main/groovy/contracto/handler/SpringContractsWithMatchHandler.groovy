package contracto.handler

import contracto.model.reflect.ContractoClassType
import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.RequestBody

import java.lang.annotation.Annotation

@CompileStatic
class SpringContractsWithMatchHandler extends DefaultContractsWithMatchHandler{

    @Override
    protected boolean withBody(Annotation[] annotations) {
        return annotations*.annotationType().contains(RequestBody)
    }

    @Override
    protected ContractoClassType.ToClass getClassTypeResolver() {
        return new ToClassImpl()
    }
}
