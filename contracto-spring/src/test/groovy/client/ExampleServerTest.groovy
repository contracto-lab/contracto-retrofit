package client

import client.controller.MyDataController
import client.controller.OtherController
import client.controller.PostController
import client.controller.ResponseEntityController
import client.controller.SecondMyDataController
import contracto.Contracto
import spock.lang.Specification

class ExampleServerTest extends Specification {
    def "Usage example"() {
        given:
        def controllers = [MyDataController, SecondMyDataController, OtherController, PostController, ResponseEntityController]
        def urls = [
                "http://localhost:13579/contracts/my_data.con.json",
                "http://localhost:13579/contracts/other_data.con.json",
                "http://localhost:13579/contracts/post_my_data.con.json"
        ]
        expect:
        new Contracto().checkContracts(controllers, urls)
    }
}
