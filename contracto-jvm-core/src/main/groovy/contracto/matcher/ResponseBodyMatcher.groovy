package contracto.matcher

import contracto.handler.DefaultContractsWithMatchHandler
import contracto.matcher.classitem.ClassItemMatcher
import contracto.model.ContractMethodMatch
import contracto.model.contract.Item
import contracto.model.error.MatchError
import contracto.model.reflect.ContractoClassType
import groovy.transform.CompileStatic

@CompileStatic
class ResponseBodyMatcher implements DefaultContractsWithMatchHandler.Matcher {

    @Override
    Collection<MatchError> isMatching(ContractMethodMatch match, ClassItemMatcher classItemMatcher) {
        ContractoClassType returnType = ContractoClassType.fromMethod(match.method)
        Item responseBody = match.getContractResponseBody()
        return classItemMatcher.checkClassMatchItem(returnType, responseBody)
    }
}
