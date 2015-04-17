package client

import client.api.MyApi
import client.api.OtherApi
import client.api.YetAnotherApi
import contracto.Cotracto
import spock.lang.Specification

class ExampleClientTest extends Specification{
    def "TADAM"() {
        given:
        def apis = [YetAnotherApi, MyApi, OtherApi]
        def urls = [
                "https://raw.githubusercontent.com/mg6maciej/contracto-retrofit/master/contracts/my_data.con.json",
                "https://raw.githubusercontent.com/mg6maciej/contracto-retrofit/master/contracts/other_data.con.json",
                "https://raw.githubusercontent.com/mg6maciej/contracto-retrofit/master/contracts/yet_another_data.con.json",
        ]

        expect:
        Cotracto.checkContracts(apis, urls)
    }
}
