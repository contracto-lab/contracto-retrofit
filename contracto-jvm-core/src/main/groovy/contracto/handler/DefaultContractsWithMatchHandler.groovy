package contracto.handler

import contracto.matcher.classitem.ClassItemMatcher
import contracto.model.ContractMethodMatch
import contracto.model.MatchError
import groovy.transform.CompileStatic

@CompileStatic
abstract class DefaultContractsWithMatchHandler {

    ClassItemMatcher classItemMatcher
    Collection<Matcher> matchers

    boolean handle(List<ContractMethodMatch> matches) {
        return matches.every({ isMatching(it) })
    }

    private boolean isMatching(ContractMethodMatch match) {
        return matchers.collectMany {
            it.isMatching(match, classItemMatcher)
        }.empty
    }

    static interface Matcher {
        Collection<MatchError> isMatching(ContractMethodMatch contractMethodMatch, ClassItemMatcher classItemMatcher)
    }
}
