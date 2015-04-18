package contracto.matcher

import contracto.handler.DefaultContractsWithMatchHandler
import contracto.model.contract.Item
import contracto.model.reflect.ContractoClassType
import spock.lang.Specification

import static contracto.model.contract.Type.*

class ContractCheckerListSpec extends Specification {

    def "Should return true when list object match body"() {
        given:
        ContractoClassType type = stringListType()
        expect:
        checkClassMatchItem(type, new Item(
                type: array,
                embedded: [
                        new Item(
                                type: string,
                        ),
                ],
        ))
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

    List<String> stringList

    private ContractoClassType stringListType() {
        def field = ContractCheckerListSpec.getDeclaredField('stringList')
        return ContractoClassType.fromField(field)
    }

    private boolean checkClassMatchItem(ContractoClassType classType, Item item) {
        return new DefaultContractsWithMatchHandler().checkClassMatchItem(classType, item)
    }
}
