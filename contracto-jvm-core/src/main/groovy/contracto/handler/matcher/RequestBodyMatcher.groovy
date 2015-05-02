package contracto.handler.matcher

import contracto.handler.DefaultContractsWithMatchHandler
import contracto.model.ContractMethodMatch
import contracto.model.contract.Item
import contracto.model.reflect.ContractoClassType
import groovy.transform.CompileStatic

@CompileStatic
class RequestBodyMatcher implements DefaultContractsWithMatchHandler.Matcher {

    @Override
    boolean isMatching(ContractMethodMatch contractMethodMatch, ClassItemMatcher classItemMatcher) {
        Item requestBody = contractMethodMatch.getContractRequestBody()
        int withBodyIndex = contractMethodMatch.method.parameterAnnotations.findIndexOf(classItemMatcher.&withBody)
        if (requestBody == null && withBodyIndex == -1) {
            return true
        }
        if (requestBody == null || withBodyIndex == -1) {
            return false
        }
        ContractoClassType type = ContractoClassType.fromParameter(contractMethodMatch.method, withBodyIndex)
        return classItemMatcher.checkClassMatchItem(type, requestBody).empty
    }
}
