package contracto.handler

import contracto.matcher.classitem.ClassItemMatcher
import contracto.model.ContractMethodMatch
import contracto.model.error.MatchError
import groovy.transform.CompileStatic

@CompileStatic
abstract class DefaultContractsWithMatchHandler {

    ClassItemMatcher classItemMatcher
    Collection<Matcher> matchers

    boolean handle(List<ContractMethodMatch> matches) {
        return matches.every({ isMatching(it) })
    }

    private boolean isMatching(ContractMethodMatch match) {
        List<MatchError> errors = matchers.collectMany {
            it.isMatching(match, classItemMatcher)
        }
        if (errors) {
            System.err.println("Error: Wrong matching for: \n$match")
            errors.each {
                System.err.println("Cause: $it")
            }
        }
        return errors.empty
    }

    static interface Matcher {
        Collection<MatchError> isMatching(ContractMethodMatch contractMethodMatch, ClassItemMatcher classItemMatcher)
    }
}
