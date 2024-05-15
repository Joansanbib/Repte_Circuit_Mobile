package ui.fragments

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reptecircuit.R
import com.example.reptecircuit.databinding.IncidenciesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ui.adapters.SearchResultsAdapter
import ui.adapters.SearchResultsInterface
import ui.viewmodels.SearchResultsViewModel
import ui.viewmodels.SearchResultsViewModelFactory


class Incidencies() : Fragment(), SearchResultsInterface {

    private lateinit var binding : IncidenciesBinding
    private lateinit var searchResultsAdapter :SearchResultsAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = IncidenciesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel : SearchResultsViewModel by viewModels { SearchResultsViewModelFactory() }
        searchResultsAdapter = SearchResultsAdapter(viewModel)

        val results = listOf("Result 1", "Result 2", "Result 3", "Resultat 4", "Resultat 5",
            "Resultat 5",  "Resultat 5",  "Resultat 5")
        viewModel.setOriginalResultsFun(results)
        viewModel.updateResultsFun(results)

        binding.sort.setOnClickListener {
            sortDialog(this.requireContext(), searchResultsAdapter, viewModel)
        }
        val viewModelContext = getViewLifecycleOwner()

        viewModel.originalResults.observe(viewModelContext){
            searchResultsAdapter.setOriginalResults(it)
        }

        viewModel.updateResults.observe(viewModelContext){
            searchResultsAdapter.updateResults(it)
        }

        viewModel.searcResults.observe(viewModelContext){
            searchResultsAdapter.search(it)
        }
    }
    fun sortDialog(context : Context, adapter: SearchResultsAdapter, viewModel: SearchResultsViewModel){
        val options = arrayOf("Opció 1", "Opció 2", "Opció 3")

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Selecciona una opció de filtratge")
            .setItems(options) { dialog, which ->
                when (which) {
                    0 -> {
                        searchEngineDialog(context, adapter, viewModel)
                        dialog.dismiss()
                    }
                    1 -> {

                    }
                    2 -> {

                    }
                }
            }

        val dialog = builder.create()
        dialog.show()
    }

    fun searchEngineDialog(context: Context, adapter: SearchResultsAdapter, viewModel: SearchResultsViewModel){
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_search, null)

        val dialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .create()


        val recycler = dialogView.findViewById<RecyclerView>(R.id.recyclerViewSearchResults)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler.layoutManager = layoutManager
        recycler.adapter = adapter

        val search = dialogView.findViewById<EditText>(R.id.editTextSearch).addTextChangedListener(
            object : TextWatcher {
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewModel.searchResultsFun(s.toString())
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun afterTextChanged(s: Editable?) {

                }
            })

        dialog.show()

    }

    override fun onItemClick() {
        TODO("Not yet implemented")
    }
}