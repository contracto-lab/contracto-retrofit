package contracto

enum Type {

    object,
    string{
        @Override
        List<Class> getPossibleClasses() {
            return [String]
        }
    },
    number{
        @Override
        List<Class> getPossibleClasses() {
            return [Integer, int, Number, double]
        }
    },
    bool{
        @Override
        List<Class> getPossibleClasses() {
            return [Boolean, boolean]
        }
    },
    array

    List<Class> getPossibleClasses() {
        return []
    }
}
