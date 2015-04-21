package contracto.model

import contracto.model.contract.Contract
import contracto.model.reflect.ContractoMethod
import groovy.transform.CompileStatic

@CompileStatic
class MatchResult {
    Collection<ContractMethodMatch> matches
    Collection<Contract> unmatchedContracts
    Collection<ContractoMethod> unmatchedMethods
}
