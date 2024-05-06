package domain.repo

import domain.entities.usuari_resol
import domain.entities.usuari_resols

interface Usuari_resolRepo {

    fun getResolComentari(coentari : String) : usuari_resols
    fun getResolId(id : Int) : usuari_resol
    fun getResolIncidencia(incidencia : Int) : usuari_resols
}