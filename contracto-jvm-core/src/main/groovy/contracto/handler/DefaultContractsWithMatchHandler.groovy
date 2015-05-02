package contracto.handler

import contracto.handler.matcher.ClassItemMatcher
import contracto.handler.matcher.RequestBodyMatcher
import contracto.handler.matcher.ResponseBodyMatcher
import contracto.model.ContractMethodMatch
import groovy.transform.CompileStatic

@CompileStatic
class DefaultContractsWithMatchHandler {

    ClassItemMatcher classItemMatcher
    Collection<Matcher> matchers = [new ResponseBodyMatcher(), new RequestBodyMatcher()]

    DefaultContractsWithMatchHandler(ClassItemMatcher classItemMatcher) {
        this.classItemMatcher = classItemMatcher
    }

    boolean handle(List<ContractMethodMatch> matches) {
        return matches.every(this.&isMatching)
    }

    private boolean isMatching(ContractMethodMatch match) {
        return matchers.every {
            it.isMatching(match, classItemMatcher)
        }
    }

    static interface Matcher {
        boolean isMatching(ContractMethodMatch contractMethodMatch, ClassItemMatcher classItemMatcher)
    }
}
