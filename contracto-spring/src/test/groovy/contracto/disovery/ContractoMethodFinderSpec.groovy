package contracto.disovery

import client.controller.MyDataController
import contracto.discovery.ContractoMethodFinder
import spock.lang.Specification

class ContractoMethodFinderSpec extends Specification {


    def "Should find method based on it annotation"() {
        given:
        def controllers = [MyDataController]

        when:
        Collection foundMethods = new ContractoMethodFinder().findMethods(controllers)

        then:
        foundMethods.size() > 0
        foundMethods*.method*.name == ['myData']
    }

}
