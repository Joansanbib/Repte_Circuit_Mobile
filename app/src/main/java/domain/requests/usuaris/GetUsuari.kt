package domain.requests.usuaris

class GetUsuari(private val usuariId : Int) {

    fun getParams() : Map<String, Int>{
        val params = HashMap<String, Int>()
        params["usuari"] = usuariId
        params["api_key"] = 1
        return params
    }

}