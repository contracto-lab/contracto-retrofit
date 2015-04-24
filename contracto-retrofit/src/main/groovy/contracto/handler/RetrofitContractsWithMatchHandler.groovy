package contracto.handler

import contracto.model.reflect.ContractoClassType
import groovy.transform.CompileStatic
import retrofit.http.Body

import java.lang.annotation.Annotation

@CompileStatic
class RetrofitContractsWithMatchHandler extends DefaultContractsWithMatchHandler{

    @Override
    protected boolean withBody(Annotation[] annotations) {
        return annotations*.annotationType().contains(Body)
    }

    @Override
    protected ContractoClassType.ToClass getClassTypeResolver() {
        return new ToClassImpl()
    }
}
