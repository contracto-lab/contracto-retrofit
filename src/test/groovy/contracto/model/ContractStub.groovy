package contracto.model

import contracto.model.contract.Contract
import contracto.model.contract.HttpMethod
import contracto.model.contract.Item
import contracto.model.contract.Meta
import contracto.model.contract.MetaRequest
import contracto.model.contract.MetaResponse
import contracto.model.contract.Request
import groovy.transform.CompileStatic

import static contracto.model.contract.Type.object
import static contracto.model.contract.Type.string

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
