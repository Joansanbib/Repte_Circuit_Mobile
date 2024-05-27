package data.services

import domain.entities.incidencia
import domain.entities.login_request
import domain.entities.usuari
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface UsuarisEndpoints {
    @POST("login")
    suspend fun login(@Header("Authorization") token: String,
                      @Body loginRequest: login_request
    ) : Response<usuari>

    @POST("register")
    suspend fun register(@Header("Authorization") token: String) : Response<usuari>
}