package client

import client.controller.MyDataController
import contracto.Contracto
import spock.lang.Specification

class ExampleServerTest extends Specification {
    def "Usage example"() {
        given:
        def controllers = [MyDataController]
        def urls = [
                "http://localhost:13579/contracts/my_data.con.json",
        ]
        expect:
        new Contracto().checkContracts(controllers, urls)
    }
}
