package contracto.model

import contracto.model.contract.Contract
import contracto.model.reflect.ContractoMethod
import groovy.transform.Canonical
import groovy.transform.CompileStatic

@Canonical
@CompileStatic
class ContractMethodMatch {

    ContractoMethod method
    Contract contract
}
