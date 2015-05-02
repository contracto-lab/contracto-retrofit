package contracto.dicovery

import client.api.BadApi
import client.data.MyData
import contracto.discovery.ContractoMethodFinder
import contracto.discovery.RetrofitAnnotatedMethodsFinder
import spock.lang.Specification

class ContractoMethodFinderSpec extends Specification {

    def "Should find only method annotated with POST"() {
        given:
        def apis = [BadApi]
        when:
        def methods = new ContractoMethodFinder(new RetrofitAnnotatedMethodsFinder()).findMethods(apis)
        then:
        methods*.method == [BadApi.getDeclaredMethod("sendMyData", MyData)]
    }
}
