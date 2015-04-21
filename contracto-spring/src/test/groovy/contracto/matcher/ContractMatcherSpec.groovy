package contracto.matcher

import spock.lang.Specification

class ContractMatcherSpec extends Specification {

//    ContractMatcher matcher = new ContractMatcher()
//
//    def "Should find matches"() {
//        given:
//        def methods = findMethods([MyApi, OtherApi])
//        def contracts = [ContractStub.contract(), ContractStub.otherContract()]
//        when:
//        List<ContractMethodMatch> matches = matcher.findMatching(methods, contracts)
//        then:
//        matches.size() == 2
//        matches.containsAll([
//                new ContractMethodMatch(method: new ContractoMethod(MyApi.methods.first()), contract: ContractStub.contract()),
//                new ContractMethodMatch(method: new ContractoMethod(OtherApi.methods.first()), contract: ContractStub.otherContract()),
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
//    public static List<ContractoMethod> findMethods(List<Class> apis) {
//        apis.collectMany {
//            it.declaredMethods.collect {
//                new ContractoMethod(it)
//            }
//        }
//    }
}
