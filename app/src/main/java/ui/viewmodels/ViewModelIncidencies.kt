package ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Tasks.await
import dagger.hilt.android.lifecycle.HiltViewModel
import domain.entities.incidencia
import domain.repo.IncidenciaRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class ViewModelIncidencies @Inject constructor(private val repository: IncidenciaRepo) : ViewModel() {

    private val _incidencies = MutableLiveData<List<incidencia>>()
    val incidencies: LiveData<List<incidencia>> = _incidencies

    private val _missatge = MutableLiveData<String>()
    val missatge: LiveData<String> = _missatge


    fun getAllIncidences() {
        viewModelScope.launch {
            try {
                val response = repository.getAllIncidences()
                _incidencies.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    fun createIncidence(incidence: incidencia) {
        viewModelScope.launch {
            try {
                val response = repository.newIncidencia(incidence)
                _incidencies.value = _incidencies.value?.plus(response)
                _missatge.value = "Incidencia creada corrèctament"
            } catch (e: Exception) {
                _missatge.value = "Incidencia incorrecta"
                e.printStackTrace()
            }
        }
    }

}