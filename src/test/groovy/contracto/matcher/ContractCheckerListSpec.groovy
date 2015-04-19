package contracto.matcher

import com.google.gson.reflect.TypeToken
import contracto.handler.DefaultContractsWithMatchHandler
import contracto.model.ContractStub
import contracto.model.contract.Item
import contracto.model.reflect.ContractoClassType
import spock.lang.Specification

import static contracto.model.contract.Type.array
import static contracto.model.contract.Type.number

class ContractCheckerListSpec extends Specification {

    def "Should return true when list object match body"() {
        given:
        ContractoClassType type = stringListType()
        expect:
        checkClassMatchItem(type, ContractStub.stringArray())
    }

    def "Should return false when list type is wrong"() {
        given:
        ContractoClassType type = stringListType()
        expect:
        !checkClassMatchItem(type, new Item(
                type: array,
                embedded: [
                        new Item(
                                type: number,
                        ),
                ],
        ))
    }

    def "Should return false when is not a list"() {
        expect:
        !checkClassMatchItem(new ContractoClassType(type: Integer), new Item(
                type: array,
                embedded: [
                        new Item(
                                type: number,
                        ),
                ],
        ))
    }

    private ContractoClassType stringListType() {
        return new ContractoClassType(type: new TypeToken<List<String>>() {}.type)
    }

    private boolean checkClassMatchItem(ContractoClassType classType, Item item) {
        return new DefaultContractsWithMatchHandler().checkClassMatchItem(classType, item)
    }
}
