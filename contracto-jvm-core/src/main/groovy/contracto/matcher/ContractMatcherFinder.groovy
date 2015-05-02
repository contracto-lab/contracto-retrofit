package contracto.matcher

import contracto.model.ContractMethodMatch
import contracto.model.MatchResult
import contracto.model.contract.Contract
import contracto.model.reflect.ContractoMethod
import groovy.transform.CompileStatic

import java.lang.reflect.Method

@CompileStatic
class ContractMatcherFinder {

    ContractMatcher contractMatcher

    ContractMatcherFinder(ContractMatcher contractMatcher) {
        this.contractMatcher = contractMatcher
    }

    MatchResult calculateMatchResult(List<Method> methods, List<Contract> contracts) {
        return new MatchResult(
                matches: findMatching(methods, contracts),
                unmatchedContracts: findContractsWithoutMatch(methods, contracts),
                unmatchedMethods: findMethodsWithoutMatch(methods, contracts)
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

    private List<Contract> findContractsWithoutMatch(List<Method> methods, List<Contract> contracts) {
        return contracts.findAll { contract ->
            !methods.any { method ->
                contractMatcher.isMatching(contract, method)
            }
        }
    }

    private List<ContractoMethod> findMethodsWithoutMatch(List<Method> methods, List<Contract> contracts) {
        return methods.findAll { method ->
            !contracts.any { contract ->
                contractMatcher.isMatching(contract, method)
            }
        }.collect{
            new ContractoMethod(it)
        }
    }

    interface ContractMatcher{
        boolean isMatching(Contract contract, Method method)
    }
}
