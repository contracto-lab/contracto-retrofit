package contracto.matcher.classitem

import contracto.matcher.RequestBodyMatcher
import groovy.transform.CompileStatic
import org.springframework.web.bind.annotation.RequestBody

import java.lang.annotation.Annotation

@CompileStatic
final class SpringRequestBodyMatcher extends RequestBodyMatcher {
    @Override
    protected boolean withBody(Annotation[] annotations) {
        return annotations*.annotationType().contains(RequestBody)
    }
}
