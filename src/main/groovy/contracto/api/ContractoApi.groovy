package contracto.api

import contracto.Contract
import retrofit.http.GET
import retrofit.http.Path

interface ContractoApi {

    @GET("/{contract}")
    Contract call(@Path("contract") String contract)
}
