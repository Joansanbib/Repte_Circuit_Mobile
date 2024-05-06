package domain.requests.incidencies

class GetIncidenciesZona(private val zona : Int) {
    fun getParams() : Map<String, Int>{
        val params = HashMap<String, Int>()
        params["prioritat"] = zona
        params["api_key"] = 1
        return params
    }
}