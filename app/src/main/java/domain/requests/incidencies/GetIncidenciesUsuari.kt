package domain.requests.incidencies

class GetIncidenciesUsuari(private val usuari : Int) {
    fun getParams() : Map<String, Int>{
        val params = HashMap<String, Int>()
        params["usuari"] = usuari
        params["api_key"] = 1
        return params
    }
}