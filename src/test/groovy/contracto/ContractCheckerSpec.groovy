package contracto

import client.data.MyData
import spock.lang.Specification

import static contracto.ContractChecker.checkClassMatchItem
import static contracto.Type.*

class ContractCheckerSpec extends Specification {

    def "Should return true when object match body"() {
        expect:
        checkClassMatchItem(MyData, newItem(object, 'id', string))
    }

    def "Should return false when class field has wrong name"() {
        expect:
        !checkClassMatchItem(MyData, newItem(object, 'wrongName', string))
    }

    def "Should return false when class field has wrong type"() {
        expect:
        !checkClassMatchItem(MyData, newItem(object, 'id', number))
    }

    def "Should return false when expected simple type"() {
        expect:
        !checkClassMatchItem(MyData, new Item(type: string))
    }

    private static Item newItem(Type type, String embeddedItemName, Type embeddedItemType) {
        return new Item(
                type: type,
                embedded: [
                        new Item(
                                name: embeddedItemName,
                                type: embeddedItemType,
                        ),
                ],
        )
    }
}
