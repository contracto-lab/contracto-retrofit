package contracto.handler

import contracto.matcher.ResponseBodyMatcher
import contracto.matcher.classitem.RetrofitRequestBodyMatcher
import groovy.transform.CompileStatic

@CompileStatic
class RetrofitContractsWithMatchHandler extends DefaultContractsWithMatchHandler {

    RetrofitContractsWithMatchHandler() {
        classItemMatcher = new RetrofitClassItemMatcher()
        matchers = [new ResponseBodyMatcher(), new RetrofitRequestBodyMatcher()] as Collection
    }
}
