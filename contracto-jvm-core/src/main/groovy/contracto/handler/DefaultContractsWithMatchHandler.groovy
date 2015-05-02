package contracto.handler

import contracto.matcher.RequestBodyMatcher
import contracto.matcher.ResponseBodyMatcher
import contracto.matcher.classitem.ClassItemMatcher
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
        return matches.every({ isMatching(it) })
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
