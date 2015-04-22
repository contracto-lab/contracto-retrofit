package contracto.matcher

import contracto.model.reflect.ContractoMethod
import spock.lang.Specification

class ContractMatcherSpec extends Specification {

//    ContractMatcherFinder matcher = new ContractMatcherFinder(new SpringContractMatcher())
//
//    def "Should find matches"() {
//        given:
//        def methods = findMethods([MyDataController])
//        def contracts = [ContractStub.contract()]
//        when:
//        List<ContractMethodMatch> matches = matcher.findMatching(methods, contracts)
//        then:
//        matches.size() == 1
//        matches.containsAll([
//                new ContractMethodMatch(method: new ContractoMethod(MyDataController.methods.first()), contract: ContractStub.contract()),
//        ])
//    }
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
}
