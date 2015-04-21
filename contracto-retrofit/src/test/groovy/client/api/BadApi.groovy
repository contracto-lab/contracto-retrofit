package client.api

import client.data.MyData
import client.data.OtherData
import retrofit.http.POST

interface BadApi {

    @POST("/")
    MyData sendMyData(MyData data)

    OtherData sendOtherData(OtherData data)
}
