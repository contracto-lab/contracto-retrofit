package contracto

import client.api.MyApi
import client.api.OtherApi
import spock.lang.Specification

class ContractMatcherSpec extends Specification {

    def "Should find matches"() {
        given:
        def methods = [MyApi, OtherApi]
        def contracts = [ContractStub.contract(), ContractStub.otherContract()]
        and:
        ContractMatcher matcher = new ContractMatcher(methods, contracts)
        when:
        List<ContractMethodMatch> matches = matcher.findMatching()
        then:
        matches.contains(new ContractMethodMatch(method: MyApi.methods.first(), contract: ContractStub.contract()))
        matches.contains(new ContractMethodMatch(method: OtherApi.methods.first(), contract: ContractStub.otherContract()))
    }

    def "Should find one match based on contract"() {
        given:
        def methods = [MyApi, OtherApi]
        def contracts = [ContractStub.contract()]
        and:
        ContractMatcher matcher = new ContractMatcher(methods, contracts)
        when:
        List<ContractMethodMatch> matches = matcher.findMatching()
        then:
        matches.contains(new ContractMethodMatch(method: MyApi.methods.first(), contract: ContractStub.contract()))
        !matches.contains(new ContractMethodMatch(method: OtherApi.methods.first(), contract: ContractStub.otherContract()))
    }

    def "Should find one match besed on methods"() {
        given:
        def methods = [MyApi]
        def contracts = [ContractStub.contract(), ContractStub.otherContract()]
        and:
        ContractMatcher matcher = new ContractMatcher(methods, contracts)
        when:
        List<ContractMethodMatch> matches = matcher.findMatching()
        then:
        matches.contains(new ContractMethodMatch(method: MyApi.methods.first(), contract: ContractStub.contract()))
        !matches.contains(new ContractMethodMatch(method: OtherApi.methods.first(), contract: ContractStub.otherContract()))
    }

    def "Should not find unmatched contracts"() {
        given:
        def methods = [MyApi, OtherApi]
        def contracts = [ContractStub.contract(), ContractStub.otherContract()]
        and:
        ContractMatcher matcher = new ContractMatcher(methods, contracts)
        when:
        List<Contract> unmatched = matcher.findContractsWithoutMatch()
        then:
        unmatched.isEmpty()
    }

    def "Should find unmatched contracts"() {
        given:
        def methods = [MyApi]
        def contracts = [ContractStub.contract(), ContractStub.otherContract()]
        and:
        ContractMatcher matcher = new ContractMatcher(methods, contracts)
        when:
        List<Contract> unmatched = matcher.findContractsWithoutMatch()
        then:
        unmatched == [ContractStub.otherContract()]
    }
}
