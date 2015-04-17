package contracto.model

import contracto.model.contract.Contract
import contracto.model.reflect.ContractoMethod
import groovy.transform.CompileStatic

@CompileStatic
class ContractMethodMatch {
    ContractoMethod method
    Contract contract
}
