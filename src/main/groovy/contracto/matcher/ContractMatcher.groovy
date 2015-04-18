package contracto.matcher

import contracto.model.ContractMethodMatch
import contracto.model.contract.Contract
import contracto.model.reflect.ContractoMethod
import groovy.transform.CompileStatic

@CompileStatic
class ContractMatcher {

    List<ContractMethodMatch> findMatching(List<ContractoMethod> methods, List<Contract> contracts) {
        return methods.collectMany { method ->
            contracts.findAll { contract ->
                contract.isMatching(method.method)
            }.collect { contract ->
                new ContractMethodMatch(method: method, contract: contract)
            }
        }
    }

    List<Contract> findContractsWithoutMatch(List<ContractoMethod> methods, List<Contract> contracts) {
        return contracts.findAll { contract ->
            !methods.any { method ->
                contract.isMatching(method.method)
            }
        }
    }

    List<ContractoMethod> findMethodsWithoutMatch(List<ContractoMethod> methods, List<Contract> contracts) {
        return methods.findAll { method ->
            !contracts*.isMatching(method.method).any()
        }
    }
}
