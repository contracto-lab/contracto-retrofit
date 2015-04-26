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
                request: new Request(
                        httpMethod: 'get',
                        path: "/my/data",
                        schema: new Schema(
                                request: new SchemaRequest(),
                                response: new SchemaResponse(
                                        body: body(),
                                ),
                        ),
                ),
                schema: new Schema(
                        request: new SchemaRequest(),
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
                request: new Request(
                        httpMethod: 'get',
                        path: "/other/data",
                        schema: new Schema(
                                request: new SchemaRequest(),
                                response: new SchemaResponse(
                                        body: otherBody(),
                                ),
                        ),
                ),
                schema: new Schema(
                        request: new SchemaRequest(),
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
