package data.services

import domain.entities.incidencia
import domain.entities.zona
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ZonesEndpoints {
    @GET("zones")
    suspend fun listAllZones(@Header("Authorization") token: String): Response<List<zona>>
}