package contracto.model.contract

import groovy.transform.Canonical
import groovy.transform.CompileStatic

import java.lang.reflect.Method

@Canonical
@CompileStatic
class Contract {

    Request request

    void displayWarning() {
        System.err.println("Warning no matching for: \n$this")
    }
}
