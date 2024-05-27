package data.implementation

import data.constants.AppConstants
import data.services.IncidenciesEndpoints
import data.services.ZonesEndpoints
import domain.entities.zona
import domain.repo.ZonesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ZonesRepoImpl @Inject constructor(
    private val zonesEndpoints: ZonesEndpoints
) : ZonesRepo {
    override suspend fun getAllZones(): List<zona> {
        return withContext(Dispatchers.IO) {
            try {
                val response = zonesEndpoints.listAllZones(AppConstants.apiKeyAdmin)
                response.body() ?: emptyList()
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        }
    }
}