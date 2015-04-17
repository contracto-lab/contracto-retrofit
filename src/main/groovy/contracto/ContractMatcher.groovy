package contracto

import groovy.transform.CompileStatic

import java.lang.reflect.Method

@CompileStatic
class ContractMatcher {
    private List<Method> methods
    private List<Contract> contracts

    ContractMatcher(List<Method> methods, List<Contract> contracts) {
        this.methods = methods
        this.contracts = contracts
    }

    List<ContractMethodMatch> findMatching() {
        List<ContractMethodMatch> matches = []
        methods.each { method ->
            contracts.each { contract ->
                if(contract.isMatching(method)){
                    matches.add(new ContractMethodMatch(method: method, contract: contract))
                }
            }
        }
        return matches
    }

    List<Contract> findContractsWithoutMatch() {
       return contracts.findAll{contract ->
            !methods.any{ method ->
                contract.isMatching(method)
            }
        }
    }
}
