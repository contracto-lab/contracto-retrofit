package contracto.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import contracto.Contract
import retrofit.RestAdapter
import retrofit.converter.GsonConverter

class ContractoService {

    List<Contract> call(Collection<String> urls) {
        return urls.collect { String url ->
            def lastSlash = url.lastIndexOf('/')
            def endpoint = url.substring(0, lastSlash)
            def contract = url.substring(lastSlash + 1)
            def adapter = new RestAdapter.Builder()
                    .setEndpoint(endpoint)
                    .setConverter(new GsonConverter(createGson()))
                    .build()
            def api = adapter.create(ContractoApi)
            return api.call(contract)
        }
    }

    private static Gson createGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }
}
