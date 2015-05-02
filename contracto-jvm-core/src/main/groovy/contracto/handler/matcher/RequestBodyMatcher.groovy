package contracto.handler.matcher

import contracto.handler.DefaultContractsWithMatchHandler
import contracto.model.ContractMethodMatch
import contracto.model.contract.Item
import contracto.model.reflect.ContractoClassType
import groovy.transform.CompileStatic

@CompileStatic
class RequestBodyMatcher implements DefaultContractsWithMatchHandler.Matcher {

    @Override
    boolean isMatching(ContractMethodMatch it, DefaultContractsWithMatchHandler contractsWithMatchHandler) {
        Item requestBody = it.getContractRequestBody()
        int withBodyIndex = it.method.parameterAnnotations.findIndexOf(contractsWithMatchHandler.&withBody)
        if (requestBody == null && withBodyIndex == -1) {
            return true
        }
        if (requestBody == null || withBodyIndex == -1) {
            return false
        }
        ContractoClassType type = ContractoClassType.fromParameter(it.method, withBodyIndex)
        return contractsWithMatchHandler.checkClassMatchItem(type, requestBody)
    }
}
