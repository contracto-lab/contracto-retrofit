package contracto

import groovy.transform.CompileStatic

@CompileStatic
class ContractChecker {

    static boolean checkClassMatchItem(Class aClass, Item item) {
        if(item.type.isSimpleType){
            return aClass in item.type.possibleClasses
        }else{
            return item.embedded.every{
                checkItemExistsInClass(aClass, it)
            }
        }
    }

    static boolean checkItemExistsInClass(Class aClass, Item item) {
        def find = aClass.declaredFields.find { it.name == item.name }
        return find ? checkClassMatchItem(find.type, item) : false
    }
}
