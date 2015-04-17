package contracto.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import contracto.Contract
import retrofit.RestAdapter
import retrofit.converter.GsonConverter

class ContractoService {

    Contract call() {
        def endpoint = "https://raw.githubusercontent.com/kv109/contracto_sample-contract/master/my_data.con.json"
        def lastSlash = endpoint.lastIndexOf('/')
        def root = endpoint.substring(0, lastSlash)
        def contract = endpoint.substring(lastSlash + 1)
        def adapter = new RestAdapter.Builder()
                .setEndpoint(root)
                .setConverter(new GsonConverter(createGson()))
                .build()
        def api = adapter.create(ContractoApi)
        return api.call(contract)
    }

    private static Gson createGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }
}
