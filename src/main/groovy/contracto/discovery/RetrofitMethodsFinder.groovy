package contracto.discovery

import retrofit.http.DELETE
import retrofit.http.GET
import retrofit.http.POST
import retrofit.http.PUT

import java.lang.annotation.Annotation
import java.lang.reflect.Method

class RetrofitMethodsFinder {

    private static final ArrayList<Class<? extends Annotation>> retrofitAnnotations = [GET, POST, PUT, DELETE]

    static Collection<Method> getRetrofitMethods(Collection<Class> classes) {
        def methods = []
        classes*.declaredMethods.each {
            methods += it.findAll(RetrofitMethodsFinder.&hasRetrofitAnnotation)
        }
        return methods
    }

    static boolean hasRetrofitAnnotation(Method method) {
        return method.declaredAnnotations*.annotationType().any { it in retrofitAnnotations }
    }
}
