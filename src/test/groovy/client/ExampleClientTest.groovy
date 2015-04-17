package client

import client.api.MyApi
import client.api.OtherApi
import contracto.Contracto
import spock.lang.Specification

class ExampleClientTest extends Specification{
    def "TADAM"() {
        given:
        def apis = [MyApi, OtherApi]
        def urls = [
                "https://raw.githubusercontent.com/contracto-lab/contracto-retrofit/master/contracts/my_data.con.json",
                "https://raw.githubusercontent.com/contracto-lab/contracto-retrofit/master/contracts/other_data.con.json",
        ]

        expect:
        new Contracto().checkContracts(apis, urls)
    }
}
