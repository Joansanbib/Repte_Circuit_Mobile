package domain.repo

import domain.entities.incidencia

interface IncidenciaRepo {
    suspend fun getAllIncidences() : List<incidencia>
    suspend fun newIncidencia(incidencia: incidencia) : incidencia


}