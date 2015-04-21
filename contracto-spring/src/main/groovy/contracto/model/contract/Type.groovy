package contracto.model.contract

enum Type {

    object([]),
    string([String]),
    number([Number, int, long, float, double]),
    bool([Boolean, boolean]),
    array([])

    final List<Class> possibleClasses

    Type(List<Class> possibleClasses) {
        this.possibleClasses = possibleClasses
    }

    boolean isSimple() {
        return possibleClasses.size() > 0
    }

    boolean isObject() {
        return this == object
    }

    boolean isArray() {
        return this == array
    }
}
