package contracto.matcher

import client.controller.CombinedController
import client.controller.MyDataController
import client.controller.OtherController
import client.controller.WrongMethodController
import contracto.model.ContractMethodMatch
import contracto.model.ContractStub
import contracto.model.contract.Contract
import contracto.model.reflect.ContractoMethod
import spock.lang.Specification

import java.lang.reflect.Method

class ContractMatcherSpec extends Specification {

    ContractMatcherFinder matcher = new ContractMatcherFinder(new SpringContractMatcher())

    def "Should find matches"() {
        given:
        def methods = findMethods([MyDataController, OtherController])
        def contracts = [ContractStub.contract(), ContractStub.otherContract()]
        when:
        List<ContractMethodMatch> matches = matcher.findMatching(methods, contracts)
        then:
        matches.size() == 2
        matches.containsAll([
                new ContractMethodMatch(method: new ContractoMethod(first(MyDataController.methods)), contract: ContractStub.contract()),
                new ContractMethodMatch(method: new ContractoMethod(first(OtherController.methods)), contract: ContractStub.otherContract())
        ])
    }
//
    def "Should find one match based on contract"() {
        given:
        def methods = findMethods([MyDataController, OtherController])
        def contracts = [ContractStub.contract()]
        when:
        List<ContractMethodMatch> matches = matcher.findMatching(methods, contracts)
        then:
        matches == [new ContractMethodMatch(method: new ContractoMethod(first(MyDataController.methods)), contract: ContractStub.contract())]
    }

    def "Should find one match besed on methods"() {
        given:
        def methods = findMethods([MyDataController])
        def contracts = [ContractStub.contract(), ContractStub.otherContract()]
        when:
        List<ContractMethodMatch> matches = matcher.findMatching(methods, contracts)
        then:
        matches == [new ContractMethodMatch(method: new ContractoMethod(first(MyDataController.methods)), contract: ContractStub.contract())]
    }

    def "Should not find unmatched contracts"() {
        given:
        def methods = findMethods([MyDataController, OtherController])
        def contracts = [ContractStub.contract(), ContractStub.otherContract()]
        when:
        List<Contract> unmatched = matcher.findContractsWithoutMatch(methods, contracts)
        then:
        unmatched.isEmpty()
    }

    def "Should find unmatched contracts"() {
        given:
        def methods = findMethods([MyDataController])
        def contracts = [ContractStub.contract(), ContractStub.otherContract()]
        when:
        List<Contract> unmatched = matcher.findContractsWithoutMatch(methods, contracts)
        then:
        unmatched == [ContractStub.otherContract()]
    }

    def "Should find unmatched methods"() {
        given:
        def methods = findMethods([MyDataController])
        when:
        List<ContractoMethod> unmatched = matcher.findMethodsWithoutMatch(methods, [])
        then:
        unmatched == [new ContractoMethod(MyDataController.getDeclaredMethod("myData"))]
    }

    def "Should find matches using path combination"() {
        given:
        def methods = findMethods([CombinedController])
        def contracts = [ContractStub.contract()]
        when:
        List<ContractMethodMatch> matches = matcher.findMatching(methods, contracts)
        then:
        matches.size() == 1
        matches.containsAll([
                new ContractMethodMatch(method: new ContractoMethod(first(CombinedController.methods)), contract: ContractStub.contract())
        ])
    }

    def "Should not find matches when controller defines wrong http method"() {
        given:
        def methods = findMethods([WrongMethodController])
        def contracts = [ContractStub.contract()]
        when:
        List<ContractMethodMatch> matches = matcher.findMatching(methods, contracts)
        then:
        matches.size() == 0
    }

    public static List<ContractoMethod> findMethods(List<Class> apis) {
        apis.collectMany {
            it.declaredMethods.findAll { !it.isSynthetic() } collect {
                new ContractoMethod(it)
            }
        }
    }

    public static Method first(Method[] methods) {
        return methods.findAll {
            !it.isSynthetic()
        }.first()
    }
}
