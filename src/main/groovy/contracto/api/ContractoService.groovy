package contracto.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.FieldNamingStrategy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import contracto.Contract
import retrofit.RestAdapter
import retrofit.converter.GsonConverter
import rx.Observable

class ContractoService {

    Contract call(String contract) {
        def adapter = new RestAdapter.Builder()
                .setEndpoint("https://raw.githubusercontent.com/kv109/contracto_sample-contract/master")
                .setConverter(new GsonConverter(createGson()))
                .build()
        def api = adapter.create(ContractoApi)
        return api.call(contract)
    }

    private Gson createGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }
}
