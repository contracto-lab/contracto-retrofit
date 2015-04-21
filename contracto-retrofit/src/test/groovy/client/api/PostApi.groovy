package client.api

import client.data.MyData
import retrofit.http.Body
import retrofit.http.POST

interface PostApi {

    @POST("/my/data")
    MyData post(@Body MyData data)
}
