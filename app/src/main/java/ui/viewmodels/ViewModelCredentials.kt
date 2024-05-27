package ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import domain.entities.incidencia
import domain.entities.usuari
import domain.repo.IncidenciaRepo
import domain.repo.UsuarisRepo
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelCredentials  @Inject constructor(private val repository: UsuarisRepo) : ViewModel() {

    private val _usuari = MutableLiveData<usuari?>()
    val usuari: LiveData<usuari?> = _usuari
    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.loginUsuari(email, password)
                _usuari.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}