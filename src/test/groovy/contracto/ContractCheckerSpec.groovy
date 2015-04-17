package contracto

import client.data.MyData
import spock.lang.Specification

import static contracto.ContractChecker.checkClassMatchItem
import static contracto.Type.*

class ContractCheckerSpec extends Specification {

    def "Should return true when object match body"() {
        expect:
        checkClassMatchItem(new ContractoClassType(type: MyData), newObjectItem('id', string))
    }

    def "Should return false when class field has wrong name"() {
        expect:
        !checkClassMatchItem(new ContractoClassType(type: MyData), newObjectItem('wrongName', string))
    }

    def "Should return false when class field has wrong type"() {
        given:
        def wrongType = number
        expect:
        !checkClassMatchItem(new ContractoClassType(type: MyData), newObjectItem('id', wrongType))
    }

    def "Should return false when expected simple type"() {
        expect:
        !checkClassMatchItem(new ContractoClassType(type: MyData), new Item(type: string))
    }

    private static Item newObjectItem(String embeddedItemName, Type embeddedItemType) {
        return new Item(
                type: object,
                embedded: [
                        new Item(
                                name: embeddedItemName,
                                type: embeddedItemType,
                        ),
                ],
        )
    }
}
