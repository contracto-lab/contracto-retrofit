package contracto.matcher

import contracto.model.reflect.ContractoClassType
import contracto.model.contract.Item
import spock.lang.Specification

import static contracto.matcher.ContractChecker.checkClassMatchItem
import static contracto.model.contract.Type.array
import static contracto.model.contract.Type.number
import static contracto.model.contract.Type.string

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
        return ContractoClassType.fromField(ContractCheckerListSpec.declaredFields.find {
            it.name == 'stringList'
        }, ContractCheckerListSpec)
    }

}