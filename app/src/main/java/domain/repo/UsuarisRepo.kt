package domain.repo

import domain.entities.usuari
import domain.entities.usuaris

interface UsuarisRepo {

    fun getUsuari(id : Int) : usuari
    fun getUsuariCognoms (cognoms : String) : usuaris
    fun getUsuariNif (nif : String) : usuari
    fun getUsuarisXrol (rol : String) : usuaris
}