package contracto

enum Type {

    object(false),
    string(true){
        @Override
        List<Class> getPossibleClasses() {
            return [String]
        }
    },
    number(true){
        @Override
        List<Class> getPossibleClasses() {
            return [Integer, int, Number, double]
        }
    },
    bool(true){
        @Override
        List<Class> getPossibleClasses() {
            return [Boolean, boolean]
        }
    },
    array(false)

    final boolean isSimpleType

    Type(boolean isSimpleType) {
        this.isSimpleType = isSimpleType
    }

    List<Class> getPossibleClasses() {
        return []
    }
}
