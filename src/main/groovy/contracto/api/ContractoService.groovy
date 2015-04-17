package contracto.api

import contracto.Contract
import retrofit.RestAdapter
import rx.Observable

class ContractoService {

    Contract call(String contract) {
        def adapter = new RestAdapter.Builder()
                .setEndpoint("https://raw.githubusercontent.com/kv109/contracto_sample-contract/master")
                .build()
        def api = adapter.create(ContractoApi)
        return api.call(contract)
    }
}
