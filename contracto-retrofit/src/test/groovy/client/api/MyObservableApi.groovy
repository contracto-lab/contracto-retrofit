package client.api

import client.data.MyData
import retrofit.http.GET
import rx.Observable

interface MyObservableApi {

    @GET("/my/data")
    Observable<MyData> callMyData()
}
