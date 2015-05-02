package contracto.model

import contracto.model.contract.Item
import contracto.model.reflect.ContractoClassType
import groovy.transform.CompileStatic
import groovy.transform.TupleConstructor

@CompileStatic
@TupleConstructor
class MatchError {

    ContractoClassType classType
    Item item


    @Override
    public String toString() {
        return """MatchError: {
    ${classType},
    ${item}
}"""
    }
}
