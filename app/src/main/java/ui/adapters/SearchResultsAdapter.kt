package ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reptecircuit.R
import ui.viewmodels.SearchResultsViewModel

class SearchResultsAdapter(
    private val viewModel: SearchResultsViewModel,
    private var onItemClicked: ((text: String) -> Unit)
) : RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>(), SearchResultsInterface {

    private var original_results: List<String> = listOf()
    private var results: List<String> = listOf()

    class ViewHolder(itemView: View, val onItemClicked: (text: String) -> Unit) : RecyclerView.ViewHolder(itemView) {
        val textViewResult: TextView = itemView.findViewById(android.R.id.text1)

        fun bind(text: String) {
            textViewResult.text = text
            itemView.setOnClickListener {
                onItemClicked(text)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(results[position]) // Llamada a bind para configurar el texto y el onClick
    }

    override fun getItemCount(): Int {
        return results.size
    }

    fun setOriginalResults(results: List<String>) {
        original_results = results
        updateResults(results)
    }

    fun updateResults(newResults: List<String>) {
        results = newResults
        notifyDataSetChanged()
    }

    fun search(text: String) {
        val filteredList = original_results.filter { it.contains(text, ignoreCase = true) }
        updateResults(filteredList)
    }

    override fun onItemClick() {
        // Implementar si es necesario
    }
}
