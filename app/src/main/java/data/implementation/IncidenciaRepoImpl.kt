package data.implementation

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import data.constants.AppConstants
import data.services.IncidenciesEndpoints
import domain.entities.incidencia
import domain.repo.IncidenciaRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class IncidenciaRepoImpl @Inject constructor(
    private val incidenciesEndpoints: IncidenciesEndpoints
    //private val coroutineScope: CoroutineScope
) : IncidenciaRepo {

    override suspend fun getAllIncidences() : List<incidencia> {
        return withContext(Dispatchers.IO) {
            try {
                val response = incidenciesEndpoints.listAllIncidences(AppConstants.apiKeyAdmin)
                response.body() ?: emptyList()
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        }
    }

    override suspend fun newIncidencia(incidencia: incidencia): incidencia {
        return withContext(Dispatchers.IO) {
            try {
                val response = incidenciesEndpoints.newIncidencia(AppConstants.apiKeyAdmin, incidencia)
                response.body() ?: incidencia
            } catch (e: Exception) {
                e.printStackTrace()
                incidencia
            }
        }
    }
}
