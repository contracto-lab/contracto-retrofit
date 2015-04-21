package client.api

import client.data.MyData
import retrofit.http.GET

interface MyApi {

    @GET("/my/data")
    MyData getMyData()
}
