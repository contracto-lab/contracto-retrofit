package contracto.matcher

import contracto.handler.RetrofitClassItemMatcher
import contracto.model.ContractMethodMatch
import contracto.model.contract.*
import spock.lang.Specification

import java.lang.reflect.Method

final class RxObservableSpec extends Specification {

    rx.Observable<String> aMethod() {
        return null
    }

    def "Should match Observable"() {
        given:
        Method method = RxObservableSpec.declaredMethods.find { it.name == 'aMethod' }
        Contract contract = new Contract(
                schema: new Schema(
                        response: new SchemaResponse(
                                body: new Item(
                                        type: JsonType.string
                                )
                        )
                )
        )

        expect:
        new ResponseBodyMatcher().isMatching(new ContractMethodMatch(method: method, contract: contract), new RetrofitClassItemMatcher()).empty
    }
}
