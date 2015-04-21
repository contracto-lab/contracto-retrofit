package client.api

import client.data.OtherData
import retrofit.http.GET

interface OtherApi {

    @GET("/other/data")
    OtherData getOtherData()
}
