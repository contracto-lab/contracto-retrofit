package contracto.model.contract

enum JsonType {

    object([]),
    string([String]),
    number([Number, int, long, float, double]),
    bool([Boolean, boolean]),
    array([])

    final List<Class> possibleClasses

    JsonType(List<Class> possibleClasses) {
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
