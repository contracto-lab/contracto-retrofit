package contracto.model

import contracto.model.contract.Contract
import contracto.model.reflect.ContractoMethod
import groovy.transform.CompileStatic

@CompileStatic
class MatchResult {
    List<ContractMethodMatch> matches
    List<Contract> unmatchedContracts
    List<ContractoMethod> unmatchedMethods
}
