import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lista3.R
import kotlinx.parcelize.Parcelize

// parcelize allows to pass data between components
@Parcelize
data class TaskList(
    val description: String,
    val points: Int
) : Parcelable

// RecyclerView Adapter for displaying detailed task information
class TaskDetailAdapter(private var task: List<TaskList>) :
    RecyclerView.Adapter<TaskDetailAdapter.TViewHolder>() {

    // ViewHolder class to hold references to views for each item in the RecyclerView
    inner class TViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val description: TextView = view.findViewById(R.id.taskDesc)
        val points: TextView = view.findViewById(R.id.points)
    }

    // inflate the layout and return a ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.e3_task, parent, false)
        return TViewHolder(view)
    }

    // bind data object to the views in the ViewHolder
    override fun onBindViewHolder(holder: TViewHolder, position: Int) {
        val task = task[position]
        holder.description.text = task.description
        holder.points.text = "Pts: ${task.points}"
    }

    override fun getItemCount(): Int = task.size

    // update the data in the adapter and refresh the RecyclerView
    fun updateData(newTask: List<TaskList>) {
        task = newTask
        // notify the adapter of data changes to refresh the UI
        notifyDataSetChanged()
    }
}
