package domain.repo

import domain.entities.usuari
import domain.entities.usuaris
import retrofit2.Response

interface UsuarisRepo {

    suspend fun loginUsuari(email : String, password : String) : usuari?
}