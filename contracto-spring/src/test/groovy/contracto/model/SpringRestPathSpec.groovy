package contracto.model

import spock.lang.Specification

class SpringRestPathSpec extends Specification {

    def "Should create proper combinations of class and method paths"() {
        expect:
            SpringRestPath.combinePaths(classPath,methodPath).containsAll(result)
        where:
            classPath   | methodPath  | result
            []          | []          | []
            ['/a']      | []          | ['/a']
            []          | ['/b']      | ['/b']
            ['/c']      | ['/d']      | ['/c/d']
            ['/a', '/b']| []          | ['/a','/b']
            ['/a', '/b']| ['/c','/d'] | ['/a/c','/a/d', '/b/c', '/b/d']
            []          | ['/c','/d'] | ['/c','/d']
    }
}
