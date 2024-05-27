package data.implementation

import data.constants.AppConstants
import data.services.UsuarisEndpoints
import domain.entities.login_request
import domain.entities.usuari
import domain.entities.usuaris
import domain.repo.UsuarisRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.internal.wait
import javax.inject.Inject

class UsuariRepoImpl @Inject constructor(
    private val usuarisEndpoints: UsuarisEndpoints
) : UsuarisRepo {
    override suspend fun loginUsuari(email: String, password: String) : usuari? {
         return withContext(Dispatchers.IO) {
            try {
                val response = usuarisEndpoints.login(AppConstants.apiKeyAdmin, login_request(email, password))
                if(response.isSuccessful){
                    response.body()
                }
                else{
                    null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}