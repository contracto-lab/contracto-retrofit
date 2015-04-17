package contracto.matcher

import contracto.model.ContractMethodMatch
import contracto.model.reflect.ContractoMethod
import contracto.model.contract.Contract
import groovy.transform.CompileStatic

@CompileStatic
class ContractMatcher {
    private List<ContractoMethod> methods
    private List<Contract> contracts

    ContractMatcher(List<Class> apis, List<Contract> contracts) {
        this.methods = findMethods(apis)
        this.contracts = contracts
    }

    private static List<ContractoMethod> findMethods(List<Class> apis) {
        List<ContractoMethod> methods = []
        for(Class api: apis){
            for (int i = 0; i < api.methods.length; i++) {
                methods.add(new ContractoMethod(api.methods[i], api))
            }
        }
        return methods
    }

    List<ContractMethodMatch> findMatching() {
        List<ContractMethodMatch> matches = []
        methods.each { method ->
            contracts.each { contract ->
                if(contract.isMatching(method.method)){
                    matches.add(new ContractMethodMatch(method: method, contract: contract))
                }
            }
        }
        return matches
    }

    List<Contract> findContractsWithoutMatch() {
       return contracts.findAll{contract ->
            !methods.any{ method ->
                contract.isMatching(method.method)
            }
        }
    }
}
