package contracto.discovery

import client.api.MyApi
import spock.lang.Specification

import java.lang.reflect.Method

class RetrofitMethodsFinderSpec extends Specification {

    def "should find getMyData method in client.api package"() {
        given:
        List<Method> retrofitMethods = RetrofitMethodsFinder.getRetrofitMethods([MyApi])
        expect:
        retrofitMethods == [MyApi.getDeclaredMethod("getMyData")]
    }
}
