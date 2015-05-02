package contracto.matcher

import client.api.MyApi
import client.api.MyObservableApi
import client.api.OtherApi
import contracto.handler.RetrofitContractsWithMatchHandler
import contracto.model.ContractMethodMatch
import contracto.model.ContractStub
import contracto.model.contract.Contract
import contracto.model.reflect.ContractoMethod
import spock.lang.Specification

class ContractMatcherSpec extends Specification {

    ContractMatcherFinder matcher = new ContractMatcherFinder(new RetrofitContractMatcher())

    def "Should find matches"() {
        given:
        def methods = findMethods([MyApi, OtherApi])
        def contracts = [ContractStub.contract(), ContractStub.otherContract()]
        when:
        List<ContractMethodMatch> matches = matcher.calculateMatchResult(methods, contracts).matches
        then:
        matches.size() == 2
        matches.containsAll([
                new ContractMethodMatch(method: new ContractoMethod(MyApi.methods.first()), contract: ContractStub.contract()),
                new ContractMethodMatch(method: new ContractoMethod(OtherApi.methods.first()), contract: ContractStub.otherContract()),
        ])
    }

    def "Should find one match based on contract"() {
        given:
        def methods = findMethods([MyApi, OtherApi])
        def contracts = [ContractStub.contract()]
        when:
        List<ContractMethodMatch> matches = matcher.calculateMatchResult(methods, contracts).matches
        then:
        matches == [new ContractMethodMatch(method: new ContractoMethod(MyApi.methods.first()), contract: ContractStub.contract())]
    }

    def "Should find one match besed on methods"() {
        given:
        def methods = findMethods([MyApi])
        def contracts = [ContractStub.contract(), ContractStub.otherContract()]
        when:
        List<ContractMethodMatch> matches = matcher.calculateMatchResult(methods, contracts).matches
        then:
        matches == [new ContractMethodMatch(method: new ContractoMethod(MyApi.methods.first()), contract: ContractStub.contract())]
    }

    def "Should not find unmatched contracts"() {
        given:
        def methods = findMethods([MyApi, OtherApi])
        def contracts = [ContractStub.contract(), ContractStub.otherContract()]
        when:
        List<Contract> unmatched = matcher.calculateMatchResult(methods, contracts).unmatchedContracts
        then:
        unmatched.isEmpty()
    }

    def "Should find unmatched contracts"() {
        given:
        def methods = findMethods([MyApi])
        def contracts = [ContractStub.contract(), ContractStub.otherContract()]
        when:
        List<Contract> unmatched = matcher.calculateMatchResult(methods, contracts).unmatchedContracts
        then:
        unmatched == [ContractStub.otherContract()]
    }

    def "Should find unmatched methods"() {
        given:
        def methods = findMethods([MyApi])
        when:
        List<ContractoMethod> unmatched = matcher.calculateMatchResult(methods, []).unmatchedMethods
        then:
        unmatched == [new ContractoMethod(MyApi.getDeclaredMethod("getMyData"))]
    }

    def "Should find match for both synchronized and observable call"() {
        given:
        def methods = findMethods([MyApi, MyObservableApi])
        def contracts = [ContractStub.contract()]
        when:
        List<ContractMethodMatch> matches = matcher.calculateMatchResult(methods, contracts).matches
        boolean result = new RetrofitContractsWithMatchHandler().handle(matches)
        then:
        result
    }

    public static List<ContractoMethod> findMethods(List<Class> apis) {
        apis.collectMany {
            it.declaredMethods.collect {
                new ContractoMethod(it)
            }
        }
    }
}
