package client

import client.api.MyApi
import client.api.OtherApi
import client.api.PostApi
import contracto.Contracto
import spock.lang.Specification

class ExampleClientTest extends Specification {
    def "TADAM"() {
        given:
        def apis = [MyApi, OtherApi, PostApi]
        def urls = [
                "http://localhost:13579/contracts/my_data.contract.json",
                "http://localhost:13579/contracts/other_data.contract.json",
                "http://localhost:13579/contracts/post_my_data.contract.json",
        ]

        expect:
        new Contracto().checkContracts(apis, urls)
    }
}
