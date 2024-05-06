package domain.requests.incidencies

class GetIncidenciesPrioritat(private val prioritat : Int) {
    fun getParams() : Map<String, Int>{
        val params = HashMap<String, Int>()
        params["prioritat"] = prioritat
        params["api_key"] = 1
        return params
    }
}