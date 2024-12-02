import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lista3.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class TaskList(
    val description: String,
    val points: Int
) : Parcelable

class TaskDetailAdapter(private var task: List<TaskList>) :
    RecyclerView.Adapter<TaskDetailAdapter.TViewHolder>() {

    inner class TViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val description: TextView = view.findViewById(R.id.taskDesc)
        val points: TextView = view.findViewById(R.id.points)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.e3_task, parent, false)
        return TViewHolder(view)
    }

    override fun onBindViewHolder(holder: TViewHolder, position: Int) {
        val task = task[position]
        holder.description.text = task.description
        holder.points.text = "Pts: ${task.points}"
    }

    override fun getItemCount(): Int = task.size

    fun updateData(newTask: List<TaskList>) {
        task = newTask
        notifyDataSetChanged()
    }
}
