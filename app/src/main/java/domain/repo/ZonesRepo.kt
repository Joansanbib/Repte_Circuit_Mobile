package domain.repo

import domain.entities.incidencia
import domain.entities.zona

interface ZonesRepo {
    suspend fun getAllZones() : List<zona>

}