package contracto

import groovy.transform.CompileStatic

import static contracto.Type.object
import static contracto.Type.string

@CompileStatic
class ContractStub {

    static Contract contract() {
        return new Contract(
                request: new Request(
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
}
