package contracto

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

import java.lang.reflect.Method

@CompileStatic
@EqualsAndHashCode
class ContractMethodMatch {
    Method method
    Contract contract
}
