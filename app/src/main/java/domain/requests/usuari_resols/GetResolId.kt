package domain.requests.usuari_resols

class GetResolId(private val id : Int) {

    fun getParams() : Map<String, Int>{
        val params = HashMap<String, Int>()
        params["id"] = id
        params["api_key"] = 1
        return params
    }
}