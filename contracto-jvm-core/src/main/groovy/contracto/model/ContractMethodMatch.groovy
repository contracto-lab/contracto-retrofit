package contracto.model

import contracto.model.contract.Contract
import contracto.model.contract.Item
import groovy.transform.Canonical
import groovy.transform.CompileStatic

import java.lang.reflect.Method

@Canonical
@CompileStatic
class ContractMethodMatch {

    Method method
    Contract contract

    Method getRegularMethod() {
        return method
    }

    Item getContractResponseBody() {
        contract.responseBody
    }

    Item getContractRequestBody() {
        contract.requestBody
    }
}
