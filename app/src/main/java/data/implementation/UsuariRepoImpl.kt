package data.implementation

import domain.entities.usuari
import domain.entities.usuaris
import domain.repo.UsuarisRepo
import javax.inject.Inject

class UsuariRepoImpl @Inject constructor() : UsuarisRepo {
    override fun getUsuari(id: Int): usuari {
        TODO("Not yet implemented")
    }

    override fun getUsuariCognoms(cognoms: String): usuaris {
        TODO("Not yet implemented")
    }

    override fun getUsuariNif(nif: String): usuari {
        TODO("Not yet implemented")
    }

    override fun getUsuarisXrol(rol: String): usuaris {
        TODO("Not yet implemented")
    }
}