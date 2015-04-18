package contracto.matcher

import client.api.MyApi
import client.api.OtherApi
import contracto.model.ContractMethodMatch
import contracto.model.ContractStub
import contracto.model.contract.Contract
import contracto.model.reflect.ContractoMethod
import spock.lang.Specification

class ContractMatcherSpec extends Specification {

    def "Should find matches"() {
        given:
        def methods = findMethods([MyApi, OtherApi])
        def contracts = [ContractStub.contract(), ContractStub.otherContract()]
        and:
        ContractMatcher matcher = new ContractMatcher()
        when:
        List<ContractMethodMatch> matches = matcher.findMatching(methods, contracts)
        then:
        matches.any {
            return it.method.method == MyApi.methods.first() &&
                    it.contract == ContractStub.contract()
        }
        matches.any {
            return it.method.method == OtherApi.methods.first() &&
                    it.contract == ContractStub.otherContract()
        }
    }

    def "Should find one match based on contract"() {
        given:
        def methods = findMethods([MyApi, OtherApi])
        def contracts = [ContractStub.contract()]
        and:
        ContractMatcher matcher = new ContractMatcher()
        when:
        List<ContractMethodMatch> matches = matcher.findMatching(methods, contracts)
        then:
        matches.any {
            return it.method.method == MyApi.methods.first() &&
                    it.contract == ContractStub.contract()
        }
        !matches.any {
            return it.method.method == OtherApi.methods.first() &&
                    it.contract == ContractStub.otherContract()
        }
    }

    def "Should find one match besed on methods"() {
        given:
        def methods = findMethods([MyApi])
        def contracts = [ContractStub.contract(), ContractStub.otherContract()]
        and:
        ContractMatcher matcher = new ContractMatcher()
        when:
        List<ContractMethodMatch> matches = matcher.findMatching(methods, contracts)
        then:
        matches.any {
            return it.method.method == MyApi.methods.first() &&
                    it.contract == ContractStub.contract()
        }
        !matches.any {
            return it.method.method == OtherApi.methods.first() &&
                    it.contract == ContractStub.otherContract()
        }
    }

    def "Should not find unmatched contracts"() {
        given:
        def methods = findMethods([MyApi, OtherApi])
        def contracts = [ContractStub.contract(), ContractStub.otherContract()]
        and:
        ContractMatcher matcher = new ContractMatcher()
        when:
        List<Contract> unmatched = matcher.findContractsWithoutMatch(methods, contracts)
        then:
        unmatched.isEmpty()
    }

    def "Should find unmatched contracts"() {
        given:
        def methods = findMethods([MyApi])
        def contracts = [ContractStub.contract(), ContractStub.otherContract()]
        and:
        ContractMatcher matcher = new ContractMatcher()
        when:
        List<Contract> unmatched = matcher.findContractsWithoutMatch(methods, contracts)
        then:
        unmatched == [ContractStub.otherContract()]
    }

    public static List<ContractoMethod> findMethods(List<Class> apis) {
        apis.collectMany { api ->
            api.declaredMethods.collect { method ->
                new ContractoMethod(method, api)
            }
        }
    }
}
