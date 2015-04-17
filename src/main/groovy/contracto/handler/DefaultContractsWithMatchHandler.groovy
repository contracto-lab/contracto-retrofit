package contracto.handler

import contracto.model.ContractMethodMatch
import groovy.transform.CompileStatic

@CompileStatic
class DefaultContractsWithMatchHandler {
    boolean handle(List<ContractMethodMatch> matches) {
        return matches.every { it.match() }
    }
}
