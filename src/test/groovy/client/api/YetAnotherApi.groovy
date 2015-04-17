package client.api

import client.data.OtherData
import client.data.YetAnotherData
import retrofit.http.GET

interface YetAnotherApi {

    @GET("/yet/another/data")
    YetAnotherData getYetAnotherData()
}
