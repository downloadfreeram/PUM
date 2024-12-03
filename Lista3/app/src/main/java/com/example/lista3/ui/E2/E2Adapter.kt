import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lista3.R

// data class to represent a Grade summary
data class Grade(
    val subject: String,
    val listNumber: Int,
    val avgGrade: Double
)

// RecyclerView Adapter for displaying Grade data in a list
class GradeAdapter(private var grades: List<Grade>) :
    RecyclerView.Adapter<GradeAdapter.GradeHolder>() {

    // ViewHolder class to hold references to the views in each list item
    inner class GradeHolder(view: View) : RecyclerView.ViewHolder(view) {
        val subject: TextView = view.findViewById(R.id.subject)
        val listCount: TextView = view.findViewById(R.id.listCount)
        val gradeAvg: TextView = view.findViewById(R.id.gradeAvg)
    }

    // inflate the layout and return a ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.e2_grade_list, parent, false)
        return GradeHolder(view)
    }

    // bind data object to the views in the ViewHolder
    override fun onBindViewHolder(holder: GradeHolder, position: Int) {
        val grade = grades[position]
        holder.subject.text = grade.subject
        holder.listCount.text = "Number of tasks: ${grade.listNumber}"
        holder.gradeAvg.text = "Average: ${grade.avgGrade}"
    }

    override fun getItemCount(): Int = grades.size

    // update the data in the adapter and refresh the RecyclerView
    fun updateData(newGrades: List<Grade>) {
        grades = newGrades
        // Notify the adapter that the data has changed, forcing a refresh
        notifyDataSetChanged()
    }
}