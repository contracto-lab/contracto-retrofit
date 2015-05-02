package contracto.handler

import groovy.transform.CompileStatic

@CompileStatic
class RetrofitContractsWithMatchHandler extends DefaultContractsWithMatchHandler{

    RetrofitContractsWithMatchHandler() {
        super(new RetrofitClassItemMatcher())
    }
}
