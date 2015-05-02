package contracto.model.error

import contracto.model.contract.Item
import contracto.model.reflect.ContractoClassType
import groovy.transform.CompileStatic
import groovy.transform.TupleConstructor

@CompileStatic
@TupleConstructor
final class FieldMissingMatchError implements MatchError {

    ContractoClassType classType
    Item item

    @Override
    public String toString() {
        return "${classType} doesn't contain field which match ${item}"
    }
}
