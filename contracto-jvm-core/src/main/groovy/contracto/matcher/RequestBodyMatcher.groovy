package contracto.matcher

import contracto.handler.DefaultContractsWithMatchHandler
import contracto.matcher.classitem.ClassItemMatcher
import contracto.model.ContractMethodMatch
import contracto.model.MatchError
import contracto.model.contract.Item
import contracto.model.reflect.ContractoClassType
import groovy.transform.CompileStatic

import java.lang.annotation.Annotation

@CompileStatic
abstract class RequestBodyMatcher implements DefaultContractsWithMatchHandler.Matcher {

    @Override
    Collection<MatchError> isMatching(ContractMethodMatch contractMethodMatch, ClassItemMatcher classItemMatcher) {
        Item requestBody = contractMethodMatch.getContractRequestBody()
        int withBodyIndex = contractMethodMatch.method.parameterAnnotations.findIndexOf(this.&withBody)
        if (requestBody == null && withBodyIndex == -1) {
            return []
        }
        if (requestBody == null || withBodyIndex == -1) {
            return []
        }
        ContractoClassType type = ContractoClassType.fromParameter(contractMethodMatch.method, withBodyIndex)
        return classItemMatcher.checkClassMatchItem(type, requestBody)
    }

    abstract protected boolean withBody(Annotation[] annotations)
}