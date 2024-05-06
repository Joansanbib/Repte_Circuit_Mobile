package domain.requests.incidencies

class GetIncidenciesEstat(private val estat : String) {
    fun getParams() : Map<String, String>{
        val params = HashMap<String, String>()
        params["estat"] = estat
        params["api_key"] = "1"
        return params
    }
}