package contracto.disovery

import client.controller.MyDataController
import client.controller.SecondMyDataController
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

    def "Should find all method when class is annotated"() {
        given:
        def controllers = [SecondMyDataController]

        when:
        Collection foundMethods = new ContractoMethodFinder().findMethods(controllers)

        then:
        foundMethods.size() > 0
        foundMethods*.method*.name == ['myData']
    }
}
