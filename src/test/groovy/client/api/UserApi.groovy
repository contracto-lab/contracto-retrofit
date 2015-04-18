package client.api

import client.data.User
import retrofit.http.GET
import retrofit.http.Path

interface UserApi {

    @GET("/users/{id}")
    User get(@Path("id") String id)
}
