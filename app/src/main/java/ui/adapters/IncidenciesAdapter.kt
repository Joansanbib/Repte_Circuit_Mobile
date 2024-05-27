package ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reptecircuit.R
import com.example.reptecircuit.databinding.IncidenciaCardBinding
import domain.entities.incidencia
import ui.viewmodels.ViewModelIncidencies
import java.text.SimpleDateFormat

class IncidenciesAdapter(var incidencies : List<incidencia>,  private val viewModel: ViewModelIncidencies
,  private var onItemClicked: ((incidencia: incidencia) -> Unit) ) :
    RecyclerView.Adapter<IncidenciesAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.incidencia_card, parent, false), viewModel, onItemClicked)
    }

    override fun getItemCount(): Int = incidencies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val incidencia = incidencies[position]
        holder.bind(incidencia)
    }

    class ViewHolder(val view: View, val viewModel : ViewModelIncidencies, val onItemClicked: (incidencia: incidencia) -> Unit) : RecyclerView.ViewHolder(view) {
            private val binding = IncidenciaCardBinding.bind(view)
            private val dateFormat = SimpleDateFormat("EEE MMM dd")

        fun bind(incidencia: incidencia) {
                binding.nomInput.text = incidencia.Nom
                binding.estatInput.text = incidencia.Estat
                binding.dataInput.text = dateFormat.format(incidencia.Data).toString()
                binding.zonaInput.text = incidencia.Zona.toString()
                binding.prioritatInput.text = incidencia.Prioritat.toString()

                binding.root.setOnClickListener {
                    onItemClicked(incidencia)
                }
            }
        }
}