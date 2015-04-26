package contracto.model

import contracto.model.contract.*
import groovy.transform.CompileStatic

import static contracto.model.contract.Type.array
import static contracto.model.contract.Type.object
import static contracto.model.contract.Type.string

@CompileStatic
class ContractStub {

    static Contract contract() {
        return new Contract(
                schema: new Schema(
                        request: new SchemaRequest(
                                method: 'get',
                                path: "/my/data",
                        ),
                        response: new SchemaResponse(
                                body: body(),
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
                schema: new Schema(
                        request: new SchemaRequest(
                                method: 'get',
                                path: "/other/data",
                        ),
                        response: new SchemaResponse(
                                body: otherBody(),
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

    static Item stringArray() {
        new Item(
                type: array,
                embedded: [
                        new Item(
                                type: string,
                        ),
                ],
        )
    }
}
