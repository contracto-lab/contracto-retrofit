package contracto.matcher

import client.data.MyData
import contracto.handler.RetrofitContractsWithMatchHandler
import contracto.model.ContractMethodMatch
import contracto.model.ContractStub
import contracto.model.contract.*
import retrofit.http.Body
import spock.lang.Specification

import java.lang.reflect.Method

class ContractCheckerBodySpec extends Specification {

    MyData justMethod(@Body MyData myData){
        //It is just a method. Only parameter is important here.
        return null;
    }
    List<String> justMethodWithStringList(@Body List<String> myData){
        //It is just a method. Only parameter is important here.
        return null;
    }

    def "Should accept match when body match parameter type"() {
        given:
        def method = aMethod('justMethod')
        def contract = aContract(ContractStub.body())
        def match = new ContractMethodMatch(method: method, contract: contract)
        expect:
        new RetrofitContractsWithMatchHandler().handle([match])
    }

    def "Should accept match when body match parameter type even when it is an array"() {
        given:
        def method = aMethod('justMethodWithStringList')
        def contract = aContract(ContractStub.stringArray())
        def match = new ContractMethodMatch(method: method, contract: contract)
        expect:
        new RetrofitContractsWithMatchHandler().handle([match])
    }

    private Contract aContract(Item body) {
        return new Contract(
                schema: new Schema(
                        request: new SchemaRequest(
                                body: body
                        ),
                        response: new SchemaResponse(
                                body: body
                        )
                )
        )
    }

    private Method aMethod(String name) {
        return ContractCheckerBodySpec.methods.find {
            it.name == name
        }
    }
}
