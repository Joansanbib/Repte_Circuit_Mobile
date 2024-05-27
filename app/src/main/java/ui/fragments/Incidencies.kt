package ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reptecircuit.R
import com.example.reptecircuit.databinding.IncidenciesBinding
import dagger.hilt.android.AndroidEntryPoint
import data.services.RetrofitConnection.builder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.create
import ui.activities.CreateIncidence
import ui.activities.IncidenciaDetall
import ui.adapters.IncidenciesAdapter
import ui.adapters.SearchResultsAdapter
import ui.adapters.SearchResultsInterface
import ui.viewmodels.ViewModelIncidencies
import ui.viewmodels.SearchResultsViewModel
import ui.viewmodels.SearchResultsViewModelFactory
import java.text.SimpleDateFormat


@AndroidEntryPoint
class Incidencies() : Fragment(), SearchResultsInterface {

    private val viewModelIncidencies: ViewModelIncidencies by viewModels()
    private lateinit var binding : IncidenciesBinding
    private lateinit var searchResultsAdapter :SearchResultsAdapter
    private lateinit var incidenciesAdapter :IncidenciesAdapter
    private var filtro : Boolean = true
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
        incidenciesAdapter = IncidenciesAdapter(emptyList(), viewModelIncidencies){ incidencia ->
            val intent : Intent = Intent(context, IncidenciaDetall::class.java)
            val dateFormat = SimpleDateFormat("EEE MMM dd")
            intent.putExtra("nom", incidencia.Nom)
            intent.putExtra("desc", incidencia.Descripcio)
            intent.putExtra("data", dateFormat.format(incidencia.Data).toString())
            intent.putExtra("estat", incidencia.Estat)
            intent.putExtra("prioritat", incidencia.Prioritat)
            intent.putExtra("rol", incidencia.Rol_assignat)
            intent.putExtra("zona", incidencia.Zona.toString())

            startActivity(intent)
        }
        GlobalScope.launch(Dispatchers.Main) {
            while (filtro){
                viewModelIncidencies.getAllIncidences()
                kotlinx.coroutines.delay(5000)
            }
        }
        val recycler = binding.recycler
        recycler.setHasFixedSize(true)
        recycler.layoutManager =  LinearLayoutManager(context)
        recycler.adapter = incidenciesAdapter

        viewModelIncidencies.incidencies.observe(viewLifecycleOwner){
            incidenciesAdapter.incidencies = it
            incidenciesAdapter.notifyDataSetChanged()
        }

        binding.sort.setOnClickListener {
            incidenciesAdapter.incidencies = viewModelIncidencies.incidencies.value!!
            sortDialog(this.requireContext(), incidenciesAdapter)
        }
        binding.newIncidence.setOnClickListener {
            val intent = Intent(context, CreateIncidence::class.java)
            startActivity(intent)
        }
    }
    fun sortDialog(context : Context, adapter: IncidenciesAdapter){
        val options = arrayOf("Nom", "Descripció", "Estat", "Prioritat", "Rol")

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Selecciona una opció de filtratge")
            .setItems(options) { dialog, which ->
                when (which) {
                    0 -> {
                        searchEngineDialog(context, adapter, "Nom")
                        dialog.dismiss()
                    }
                    1 -> {
                        searchEngineDialog(context, adapter, "Descripció")
                        dialog.dismiss()
                    }
                    2 -> {
                        searchEngineDialog(context, adapter, "Estat")
                        dialog.dismiss()
                    }
                    3 -> {
                        searchEngineDialog(context, adapter, "Prioritat")
                        dialog.dismiss()
                    }
                    4 -> {
                        searchEngineDialog(context, adapter, "Rol")
                        dialog.dismiss()
                    }

                }
            }

        val dialog = builder.create()
        dialog.show()
    }

    fun searchEngineDialog(context: Context, adapter: IncidenciesAdapter, camp : String){
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_search, null)

        val dialog = AlertDialog.Builder(context)
            .setView(dialogView)
            .create()

        val viewModel: SearchResultsViewModel by viewModels { SearchResultsViewModelFactory() }
        val adapterSearch = SearchResultsAdapter(viewModel){busqueda->
            incidenciesAdapter.incidencies = viewModelIncidencies.incidencies.value!!
            when (camp){
                "Nom" -> {
                    incidenciesAdapter.incidencies = incidenciesAdapter.incidencies.filter { it.Nom == busqueda  }
                    incidenciesAdapter.notifyDataSetChanged()
                    filtro = false
                }
                "Descripció" -> {
                    incidenciesAdapter.incidencies = incidenciesAdapter.incidencies.filter { it.Descripcio == busqueda  }
                    incidenciesAdapter.notifyDataSetChanged()
                    filtro = false
                }
                "Estat" -> {
                    incidenciesAdapter.incidencies = incidenciesAdapter.incidencies.filter { it.Estat == busqueda }
                    incidenciesAdapter.notifyDataSetChanged()
                    filtro = false
                }
                "Prioritat" -> {
                    incidenciesAdapter.incidencies = incidenciesAdapter.incidencies.filter { it.Prioritat == busqueda  }
                    incidenciesAdapter.notifyDataSetChanged()
                    filtro = false
                }
                "Rol" -> {
                    incidenciesAdapter.incidencies = incidenciesAdapter.incidencies.filter { it.Rol_assignat == busqueda  }
                    incidenciesAdapter.notifyDataSetChanged()
                    filtro = false
                }
            }
        }
        val recycler = dialogView.findViewById<RecyclerView>(R.id.recyclerViewSearchResults)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler.layoutManager = layoutManager

        when (camp){
            "Nom" -> {
                adapterSearch.setOriginalResults(adapter.incidencies.map { it.Nom })
            }
            "Descripció" -> {
                adapterSearch.setOriginalResults(adapter.incidencies.map { it.Descripcio })
            }
            "Estat" -> {
                adapterSearch.setOriginalResults(adapter.incidencies.map { it.Estat })
            }
            "Prioritat" -> {
                adapterSearch.setOriginalResults(adapter.incidencies.map { it.Prioritat })
            }
            "Rol" -> {
                adapterSearch.setOriginalResults(adapter.incidencies.map { it.Rol_assignat })
            }
        }
        recycler.adapter = adapterSearch


        val search = dialogView.findViewById<EditText>(R.id.editTextSearch).addTextChangedListener(
            object : TextWatcher {
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    adapterSearch.search(s.toString())
                    recycler.adapter = adapterSearch
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