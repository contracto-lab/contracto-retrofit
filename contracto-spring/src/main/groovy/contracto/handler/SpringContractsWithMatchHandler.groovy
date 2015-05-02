package contracto.handler

import contracto.matcher.ResponseBodyMatcher
import contracto.matcher.classitem.SpringRequestBodyMatcher
import groovy.transform.CompileStatic

@CompileStatic
class SpringContractsWithMatchHandler extends DefaultContractsWithMatchHandler {

    SpringContractsWithMatchHandler() {
        classItemMatcher = new SpringClassItemMatcher()
        matchers = [new ResponseBodyMatcher(), new SpringRequestBodyMatcher()] as Collection
    }

}
