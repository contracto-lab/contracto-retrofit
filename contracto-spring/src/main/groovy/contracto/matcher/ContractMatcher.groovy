package contracto.matcher

import contracto.model.ContractMethodMatch
import contracto.model.MatchResult
import contracto.model.SpringRest
import contracto.model.contract.Contract
import contracto.model.reflect.ContractoMethod
import groovy.transform.CompileStatic

@CompileStatic
class ContractMatcher {

    List<ContractMethodMatch> findMatching(List<ContractoMethod> methods, List<Contract> contracts) {
        return methods.collectMany { method ->
            contracts.findAll { contract ->
                isMatching(contract, method)
            }.collect { contract ->
                new ContractMethodMatch(method: method, contract: contract)
            }
        }
    }

    List<Contract> findContractsWithoutMatch(List<ContractoMethod> methods, List<Contract> contracts) {
        return contracts.findAll { contract ->
            !methods.any { method ->
                isMatching(contract, method)
            }
        }
    }

    List<ContractoMethod> findMethodsWithoutMatch(List<ContractoMethod> methods, List<Contract> contracts) {
        return methods.findAll { method ->
            !contracts.any { contract ->
                isMatching(contract, method)
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

    private boolean isMatching(Contract contract, ContractoMethod method) {
        return SpringRest.from(method.method).matches(contract.request.path)
    }
}
