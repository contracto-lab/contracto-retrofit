package contracto

import client.data.MyData
import spock.lang.Specification

import static contracto.ContractChecker.checkClassMatchItem
import static contracto.Type.*

class ContractCheckerSpec extends Specification {

    def "Should return true when object match body"() {
        expect:
        checkClassMatchItem(MyData, newItem(object, 'id', string, null))
    }

    def "Should return false when object doesn't match body"() {
        expect:
        !checkClassMatchItem(MyData, newItem(type, embeddedItemName, embeddedItemType, embeddedembedded))
        where:
        type   | embeddedItemName | embeddedItemType | embeddedembedded
        object | 'name'           | string           | null
        object | 'id'             | number           | null
        object | 'id'             | object           | [newItem(string, null, null, null)]
    }

    private static Item newItem(Type type, String embeddedItemName, Type embeddedItemType, List<Item> embeddedembedded) {
        return new Item(
                type: type,
                embedded: [
                        new Item(
                                name: embeddedItemName,
                                type: embeddedItemType,
                                embedded: embeddedembedded,
                        ),
                ],
        )
    }
}
