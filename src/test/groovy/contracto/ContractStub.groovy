package contracto

import groovy.transform.CompileStatic

import static contracto.Type.object
import static contracto.Type.string

@CompileStatic
class ContractStub {

    static Contract contract() {
        return new Contract(
                request: new Request(
                        httpMethod: HttpMethod.get,
                        path: "/my/data",
                        meta: new Meta(
                                request: new MetaRequest(),
                                response: new MetaResponse(
                                        body: body(),
                                ),
                        ),
                ),
        )
    }

    static Item body() {
        new Item(
                type: object,
                embedded: [
                        new Item(
                                name: 'id',
                                type: string,
                        ),
                ],
        )
    }

    static Contract otherContract() {
        return new Contract(
                request: new Request(
                        httpMethod: HttpMethod.get,
                        path: "/other/data",
                        meta: new Meta(
                                request: new MetaRequest(),
                                response: new MetaResponse(
                                        body: otherBody(),
                                ),
                        ),
                ),
        )
    }

    static Item otherBody() {
        new Item(
                type: object,
                embedded: [
                        new Item(
                                name: 'name',
                                type: string,
                        ),
                        new Item(
                                name: 'surname',
                                type: string,
                        ),
                ],
        )
    }
}
