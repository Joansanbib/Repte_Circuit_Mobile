package data.implementation

import domain.entities.usuari_resol
import domain.entities.usuari_resols
import domain.repo.Usuari_resolRepo

class Usuari_resolRepoImpl constructor() : Usuari_resolRepo {
    override fun getResolComentari(coentari: String): usuari_resols {
        TODO("Not yet implemented")
    }

    override fun getResolId(id: Int): usuari_resol {
        TODO("Not yet implemented")
    }

    override fun getResolIncidencia(incidencia: Int): usuari_resols {
        TODO("Not yet implemented")
    }
}