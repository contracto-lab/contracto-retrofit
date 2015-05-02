package contracto.handler

import contracto.matcher.classitem.ClassItemMatcher
import contracto.model.ContractMethodMatch
import groovy.transform.CompileStatic

@CompileStatic
abstract class DefaultContractsWithMatchHandler {

    ClassItemMatcher classItemMatcher
    Collection<Matcher> matchers

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
