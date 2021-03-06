package contracto.matcher

import client.controller.*
import contracto.handler.ContractMatcherFinder
import contracto.handler.SpringContractsWithMatchHandler
import contracto.model.ContractMethodMatch
import contracto.model.ContractStub
import contracto.model.contract.Contract
import spock.lang.Specification

import java.lang.reflect.Method

class ContractMatcherSpec extends Specification {

    ContractMatcherFinder matcher = new ContractMatcherFinder(new SpringContractMatcher())

    def "Should find matches"() {
        given:
        def methods = findMethods([MyDataController, OtherController])
        def contracts = [ContractStub.contract(), ContractStub.otherContract()]
        when:
        List<ContractMethodMatch> matches = matcher.calculateMatchResult(methods, contracts).matches
        then:
        matches.size() == 2
        matches.containsAll([
                new ContractMethodMatch(method: first(MyDataController.methods), contract: ContractStub.contract()),
                new ContractMethodMatch(method: first(OtherController.methods), contract: ContractStub.otherContract())
        ])
    }

    def "Should find one match based on contract"() {
        given:
        def methods = findMethods([MyDataController, OtherController])
        def contracts = [ContractStub.contract()]
        when:
        List<ContractMethodMatch> matches = matcher.calculateMatchResult(methods, contracts).matches
        then:
        matches == [new ContractMethodMatch(method: first(MyDataController.methods), contract: ContractStub.contract())]
    }

    def "Should find one match besed on methods"() {
        given:
        def methods = findMethods([MyDataController])
        def contracts = [ContractStub.contract(), ContractStub.otherContract()]
        when:
        List<ContractMethodMatch> matches = matcher.calculateMatchResult(methods, contracts).matches
        then:
        matches == [new ContractMethodMatch(method: first(MyDataController.methods), contract: ContractStub.contract())]
    }

    def "Should not find unmatched contracts"() {
        given:
        def methods = findMethods([MyDataController, OtherController])
        def contracts = [ContractStub.contract(), ContractStub.otherContract()]
        when:
        List<Contract> unmatched = matcher.calculateMatchResult(methods, contracts).unmatchedContracts
        then:
        unmatched.isEmpty()
    }

    def "Should find unmatched contracts"() {
        given:
        def methods = findMethods([MyDataController])
        def contracts = [ContractStub.contract(), ContractStub.otherContract()]
        when:
        List<Contract> unmatched = matcher.calculateMatchResult(methods, contracts).unmatchedContracts
        then:
        unmatched == [ContractStub.otherContract()]
    }

    def "Should find unmatched methods"() {
        given:
        def methods = findMethods([MyDataController])
        when:
        List<Method> unmatched = matcher.calculateMatchResult(methods, []).unmatchedMethods
        then:
        unmatched == [MyDataController.getDeclaredMethod("myData")]
    }

    def "Should find matches using path combination"() {
        given:
        def methods = findMethods([CombinedController])
        def contracts = [ContractStub.contract()]
        when:
        List<ContractMethodMatch> matches = matcher.calculateMatchResult(methods, contracts).matches
        then:
        matches.size() == 1
        matches.containsAll([
                new ContractMethodMatch(method: first(CombinedController.methods), contract: ContractStub.contract())
        ])
    }

    def "Should not find matches when controller defines wrong http method"() {
        given:
        def methods = findMethods([WrongMethodController])
        def contracts = [ContractStub.contract()]
        when:
        List<ContractMethodMatch> matches = matcher.calculateMatchResult(methods, contracts).matches
        then:
        matches.size() == 0
    }

    def "Should not match using default method when other annotation defines http method"() {
        given:
        def methods = findMethods([SecWrongMethodController])
        def contracts = [ContractStub.contract()]
        when:
        List<ContractMethodMatch> matches = matcher.calculateMatchResult(methods, contracts).matches
        then:
        matches.size() == 0
    }

    def "Should find match for both ResponseEntity wrapped object and object"() {
        given:
        def methods = findMethods([MyDataController, ResponseEntityController])
        def contracts = [ContractStub.contract()]
        when:
        List<ContractMethodMatch> matches = matcher.calculateMatchResult(methods, contracts).matches
        boolean result = new SpringContractsWithMatchHandler().handle(matches)
        then:
        result
    }

    public static List<Method> findMethods(List<Class> apis) {
        apis.collectMany {
            it.declaredMethods.findAll { !it.isSynthetic() }
        }
    }

    public static Method first(Method[] methods) {
        return methods.findAll {
            !it.isSynthetic()
        }.first()
    }
}
