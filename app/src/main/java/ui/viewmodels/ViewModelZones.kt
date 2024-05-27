package ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import domain.entities.incidencia
import domain.entities.zona
import domain.repo.IncidenciaRepo
import domain.repo.ZonesRepo
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelZones @Inject constructor(private val repository: ZonesRepo) : ViewModel() {

    private val _zones = MutableLiveData<List<zona>>()
    val zones: LiveData<List<zona>> = _zones

    fun getAllZones(){
        viewModelScope.launch {
            try {
                val response = repository.getAllZones()
                _zones.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}