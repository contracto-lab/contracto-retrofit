package contracto.matcher

import contracto.model.ContractMethodMatch
import contracto.model.MatchResult
import contracto.model.contract.Contract
import groovy.transform.CompileStatic

import java.lang.reflect.Method

@CompileStatic
class ContractMatcherFinder {

    ContractMatcher contractMatcher

    ContractMatcherFinder(ContractMatcher contractMatcher) {
        this.contractMatcher = contractMatcher
    }

    MatchResult calculateMatchResult(List<Method> methods, List<Contract> contracts) {
        List<ContractMethodMatch> matching = findMatching(methods, contracts)
        List<Contract> unmatchedContracts = contracts - matching*.contract
        List<Method> unmatchedMethods = methods - matching*.method
        return new MatchResult(
                matches: matching,
                unmatchedContracts: unmatchedContracts,
                unmatchedMethods: unmatchedMethods,
        )
    }

    private List<ContractMethodMatch> findMatching(List<Method> methods, List<Contract> contracts) {
        return methods.collectMany { method ->
            contracts.findAll { contract ->
                contractMatcher.isMatching(contract, method)
            }.collect { contract ->
                new ContractMethodMatch(method: method, contract: contract)
            }
        }
    }

    interface ContractMatcher{
        boolean isMatching(Contract contract, Method method)
    }
}
