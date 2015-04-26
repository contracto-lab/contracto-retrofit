package contracto.matcher

import client.data.MyData
import contracto.handler.RetrofitContractsWithMatchHandler
import contracto.model.ContractMethodMatch
import contracto.model.ContractStub
import contracto.model.contract.*
import contracto.model.reflect.ContractoMethod
import retrofit.http.Body
import spock.lang.Specification

class ContractCheckerBodySpec extends Specification {

    void justMethod(@Body MyData myData){
        //It is just a method. Only parameter is important here.
    }
    void justMethodWithStringList(@Body List<String> myData){
        //It is just a method. Only parameter is important here.
    }

    def "Should accept match when body match parameter type"() {
        given:
        def method = aMethod('justMethod')
        def contract = aContract(ContractStub.body())
        def match = new ContractMethodMatch(method: method, contract: contract)
        expect:
        new RetrofitContractsWithMatchHandler().checkRequestBody(match)
    }

    def "Should accept match when body match parameter type even when it is an array"() {
        given:
        def method = aMethod('justMethodWithStringList')
        def contract = aContract(ContractStub.stringArray())
        def match = new ContractMethodMatch(method: method, contract: contract)
        expect:
        new RetrofitContractsWithMatchHandler().checkRequestBody(match)
    }

    private Contract aContract(Item body) {
        return new Contract(
                request: new Request(
                        schema: new Schema(
                                request: new SchemaRequest(
                                        body: body
                                )
                        )
                ),
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
