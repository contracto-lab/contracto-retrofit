package contracto.matcher

import client.controller.MyDataController
import client.controller.OtherController
import contracto.model.ContractMethodMatch
import contracto.model.ContractStub
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
//    def "Should find one match based on contract"() {
//        given:
//        def methods = findMethods([MyApi, OtherApi])
//        def contracts = [ContractStub.contract()]
//        when:
//        List<ContractMethodMatch> matches = matcher.findMatching(methods, contracts)
//        then:
//        matches == [new ContractMethodMatch(method: new ContractoMethod(MyApi.methods.first()), contract: ContractStub.contract())]
//    }
//
//    def "Should find one match besed on methods"() {
//        given:
//        def methods = findMethods([MyApi])
//        def contracts = [ContractStub.contract(), ContractStub.otherContract()]
//        when:
//        List<ContractMethodMatch> matches = matcher.findMatching(methods, contracts)
//        then:
//        matches == [new ContractMethodMatch(method: new ContractoMethod(MyApi.methods.first()), contract: ContractStub.contract())]
//    }
//
//    def "Should not find unmatched contracts"() {
//        given:
//        def methods = findMethods([MyApi, OtherApi])
//        def contracts = [ContractStub.contract(), ContractStub.otherContract()]
//        when:
//        List<Contract> unmatched = matcher.findContractsWithoutMatch(methods, contracts)
//        then:
//        unmatched.isEmpty()
//    }
//
//    def "Should find unmatched contracts"() {
//        given:
//        def methods = findMethods([MyApi])
//        def contracts = [ContractStub.contract(), ContractStub.otherContract()]
//        when:
//        List<Contract> unmatched = matcher.findContractsWithoutMatch(methods, contracts)
//        then:
//        unmatched == [ContractStub.otherContract()]
//    }
//
//    def "Should find unmatched methods"() {
//        given:
//        def methods = findMethods([MyApi])
//        when:
//        List<ContractoMethod> unmatched = matcher.findMethodsWithoutMatch(methods, [])
//        then:
//        unmatched == [new ContractoMethod(MyApi.getDeclaredMethod("getMyData"))]
//    }
//
//    def "Should find match for both synchronized and observable call"() {
//        given:
//        def methods = findMethods([MyApi, MyObservableApi])
//        def contracts = [ContractStub.contract()]
//        when:
//        List<ContractMethodMatch> matches = matcher.findMatching(methods, contracts)
//        boolean result = new DefaultContractsWithMatchHandler().handle(matches)
//        then:
//        result
//    }
//
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
