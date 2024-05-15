package ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ui.viewmodels.SearchResultsViewModel
import javax.inject.Inject


class SearchResultsAdapter (private val viewModel: SearchResultsViewModel) : RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>(),
    SearchResultsInterface {

    private var original_results : List<String> = listOf()
    private var results: List<String> = listOf()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewResult: TextView = itemView.findViewById(android.R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewResult.text = results[position]
    }

    override fun getItemCount(): Int {
        return results.size
    }

     fun setOriginalResults(results : List<String>) {
        original_results = results
    }

     fun updateResults(newResults : List<String>) {
        results = newResults
        notifyDataSetChanged()
    }

     fun search(text : String) {
        val filteredList = results.filter { it.contains(text, ignoreCase = true) }.toList()
        val filteredListOriginals = original_results.filter {  it.contains(text, ignoreCase = true) }.toList()
        if(filteredList.size>filteredListOriginals.size) updateResults(filteredList)
        else {
            updateResults(filteredListOriginals)
        }
    }

    override fun onItemClick() {
        TODO("Not yet implemented")
    }
}
