package data.services

import domain.entities.incidencia
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface IncidenciesEndpoints {
    @GET("incidencias")
    suspend fun listAllIncidences(@Header("Authorization") token: String): Response<List<incidencia>>

    @POST("incidencias")
    suspend fun newIncidencia(
        @Header("Authorization") token: String,
        @Body incidencia: incidencia
    ): Response<incidencia>



}