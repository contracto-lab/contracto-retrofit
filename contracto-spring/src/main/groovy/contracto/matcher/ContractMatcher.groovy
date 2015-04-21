package contracto.matcher

import contracto.model.ContractMethodMatch
import contracto.model.MatchResult
import contracto.model.contract.Contract
import contracto.model.reflect.ContractoMethod
import groovy.transform.CompileStatic

@CompileStatic
class ContractMatcher {

    Collection<ContractMethodMatch> findMatching(Collection<ContractoMethod> methods, Collection<Contract> contracts) {
        return methods.collectMany { method ->
            contracts.findAll { contract ->
                contract.isMatching(method.method)
            }.collect { contract ->
                new ContractMethodMatch(method: method, contract: contract)
            }
        }
    }

    Collection<Contract> findContractsWithoutMatch(Collection<ContractoMethod> methods, Collection<Contract> contracts) {
        return contracts.findAll { contract ->
            !methods.any { method ->
                contract.isMatching(method.method)
            }
        }
    }

    Collection<ContractoMethod> findMethodsWithoutMatch(Collection<ContractoMethod> methods, Collection<Contract> contracts) {
        return methods.findAll { method ->
            !contracts*.isMatching(method.method).any()
        }
    }

    MatchResult calculateMatchResult(Collection<ContractoMethod> contractoMethods, Collection<Contract> contracts) {
        return new MatchResult(
                matches: findMatching(contractoMethods, contracts),
                unmatchedContracts: findContractsWithoutMatch(contractoMethods, contracts),
                unmatchedMethods: findMethodsWithoutMatch(contractoMethods, contracts)
        )
    }
}
