package contracto.handler

import groovy.transform.CompileStatic

@CompileStatic
class SpringContractsWithMatchHandler extends DefaultContractsWithMatchHandler{

    SpringContractsWithMatchHandler() {
        super(new SpringClassItemMatcher())
    }

}
