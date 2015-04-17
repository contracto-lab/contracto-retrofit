package contracto

import groovy.transform.CompileStatic

@CompileStatic
class ContractChecker {

    static boolean checkObjectMatchBody(Class aClass, Item body) {
        return body.embedded.first().name == aClass.declaredFields.first().name
    }

    static boolean checkClassMatchItem(Class aClass, Item item) {
        if(item.type.isSimpleType){
            return aClass in item.type.possibleClasses
        }else{
            def embeddedItem = item.embedded.first()
            def field = aClass.declaredFields.first()
            return embeddedItem.name == field.name && checkClassMatchItem(field.type, embeddedItem)
        }
    }
}
