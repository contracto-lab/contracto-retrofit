package contracto.matcher

import client.model.MyData
import contracto.handler.SpringContractsWithMatchHandler
import contracto.model.ContractMethodMatch
import contracto.model.ContractStub
import contracto.model.contract.*
import contracto.model.reflect.ContractoMethod
import org.springframework.web.bind.annotation.RequestBody
import spock.lang.Specification

class ContractCheckerBodySpec extends Specification {

    void justMethod(@RequestBody MyData myData) {
        //It is just a method. Only parameter is important here.
    }

    void justMethodWithStringList(@RequestBody List<String> myData) {
        //It is just a method. Only parameter is important here.
    }

    def "Should accept match when body match parameter type"() {
        given:
        def method = aMethod('justMethod')
        def contract = aContract(ContractStub.body())
        def match = new ContractMethodMatch(method: method, contract: contract)
        expect:
        new SpringContractsWithMatchHandler().checkRequestBody(match)
    }

    def "Should accept match when body match parameter type even when it is an array"() {
        given:
        def method = aMethod('justMethodWithStringList')
        def contract = aContract(ContractStub.stringArray())
        def match = new ContractMethodMatch(method: method, contract: contract)
        expect:
        new SpringContractsWithMatchHandler().checkRequestBody(match)
    }

    private Contract aContract(Item body) {
        return new Contract(
                schema: new Schema(
                        request: new SchemaRequest(
                                body: body
                        )
                )
        )
    }

    private ContractoMethod aMethod(String name) {
        return new ContractoMethod(ContractCheckerBodySpec.methods.find {
            it.name == name
        })
    }
}
