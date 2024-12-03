import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lista3.R
import kotlin.random.Random
import kotlinx.parcelize.Parcelize

// parcelize allows to pass data between components
@Parcelize
data class Tasks(
    val subject: String,
    val taskName: String,
    val listCount: Int,
    val totalPoints: Int,
    val grade: Double
) : Parcelable

// generate task lists with random values
fun generateTaskLists(): List<Tasks> {
    val subjects = listOf("Matematyka", "PUM", "Fizyka", "Elektronika", "Algorytmy")
    // dictionary to store each subject lists number
    val listCounters = mutableMapOf<String, Int>()
    val taskLists = mutableListOf<Tasks>()

    for (i in 1..20) {
        val subject = subjects.random()

        // increment list number by one, or initialize from one
        val taskNumber = listCounters.getOrDefault(subject, 0) + 1
        listCounters[subject] = taskNumber

        val taskCount = Random.nextInt(1, 11)
        val totalPoints = taskCount * Random.nextInt(1, 11)
        val grade = Random.nextDouble(3.0, 5.1).let {
            (it * 2).toInt() / 2.0
        }

        val taskList = Tasks(
            subject = subject,
            taskName = "Lista $taskNumber",
            listCount = taskCount,
            totalPoints = totalPoints,
            grade = grade
        )
        taskLists.add(taskList)
    }

    return taskLists
}
// adapter to bind task list data to a RecyclerView
class TaskListAdapter(private var taskList: List<Tasks>,
                      private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

    //class to hold references to the views in each item layout
    inner class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val subject: TextView = view.findViewById(R.id.subject)
        val listCount: TextView = view.findViewById(R.id.listCount)
        val taskName: TextView = view.findViewById(R.id.taskName)
        val grade: TextView = view.findViewById(R.id.grade)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.e1_task_list, parent, false)
        return TaskViewHolder(view)
    }

    // bind data to the views in the ViewHolder
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.subject.text = task.subject
        holder.listCount.text = "Number of tasks: ${task.listCount}"
        holder.taskName.text = task.taskName
        holder.grade.text = "Grade: ${task.grade}"

        // set a click listener for the item view
        holder.itemView.setOnClickListener {
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(task)
            }
        }
    }

    override fun getItemCount(): Int = taskList.size

    // update the data in the adapter and refresh the RecyclerView
    fun updateData(newTaskLists: List<Tasks>) {
        taskList = newTaskLists
        // notify the adapter of data changes to refresh the UI
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(taskList: Tasks)
    }

}

//private fun AdapterView.OnItemClickListener.onItemClick(task: Tasks) {}