package contracto.matcher.classitem

import contracto.matcher.RequestBodyMatcher
import groovy.transform.CompileStatic
import retrofit.http.Body

import java.lang.annotation.Annotation

@CompileStatic
final class RetrofitRequestBodyMatcher extends RequestBodyMatcher {
    @Override
    protected boolean withBody(Annotation[] annotations) {
        return annotations*.annotationType().contains(Body)
    }
}
