package contracto.matcher

import contracto.model.ContractMethodMatch
import contracto.model.MatchResult
import contracto.model.contract.Contract
import contracto.model.reflect.ContractoMethod
import groovy.transform.CompileStatic

@CompileStatic
class ContractMatcherFinder {

    ContractMatcher contractMatcher

    ContractMatcherFinder(ContractMatcher contractMatcher) {
        this.contractMatcher = contractMatcher
    }

    List<ContractMethodMatch> findMatching(List<ContractoMethod> methods, List<Contract> contracts) {
        return methods.collectMany { method ->
            contracts.findAll { contract ->
                contractMatcher.isMatching(contract, method)
            }.collect { contract ->
                new ContractMethodMatch(method: method, contract: contract)
            }
        }
    }

    List<Contract> findContractsWithoutMatch(List<ContractoMethod> methods, List<Contract> contracts) {
        return contracts.findAll { contract ->
            !methods.any { method ->
                contractMatcher.isMatching(contract, method)
            }
        }
    }

    List<ContractoMethod> findMethodsWithoutMatch(List<ContractoMethod> methods, List<Contract> contracts) {
        return methods.findAll { method ->
            !contracts.any { contract ->
                contractMatcher.isMatching(contract, method)
            }
        }
    }

    MatchResult calculateMatchResult(List<ContractoMethod> contractoMethods, List<Contract> contracts) {
        return new MatchResult(
                matches: findMatching(contractoMethods, contracts),
                unmatchedContracts: findContractsWithoutMatch(contractoMethods, contracts),
                unmatchedMethods: findMethodsWithoutMatch(contractoMethods, contracts)
        )
    }

    interface ContractMatcher{
        boolean isMatching(Contract contract, ContractoMethod method)
    }
}
