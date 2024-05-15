package ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SearchResultsViewModel: ViewModel() {


        private val _originalResults = MutableLiveData<List<String>>()
        fun setOriginalResultsFun(results: List<String>) {
            _originalResults.value = results
        }
        val originalResults: LiveData<List<String>>
            get() = _originalResults

        private val _updateResults = MutableLiveData<List<String>>()

        fun updateResultsFun(results: List<String>) {
            _updateResults.value = results
        }
        val updateResults: LiveData<List<String>>
            get() = _updateResults

        private val _searchResults = MutableLiveData<String>()
        val searcResults: LiveData<String>
            get() = _searchResults

        fun searchResultsFun(text: String) {
            _searchResults.value = text
        }

}
@Suppress("UNCHECKED_CAST")
class SearchResultsViewModelFactory(): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        return SearchResultsViewModel() as T
    }
}