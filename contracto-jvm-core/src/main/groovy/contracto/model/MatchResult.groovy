package contracto.model

import contracto.model.contract.Contract
import groovy.transform.CompileStatic

import java.lang.reflect.Method

@CompileStatic
class MatchResult {
    List<ContractMethodMatch> matches
    List<Contract> unmatchedContracts
    List<Method> unmatchedMethods
}
