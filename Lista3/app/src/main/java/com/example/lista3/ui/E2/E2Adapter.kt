import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lista3.R

data class Grade(
    val subject: String,
    val listNumber: Int,
    val avgGrade: Double
)
class GradeAdapter(private var grades: List<Grade>) :
    RecyclerView.Adapter<GradeAdapter.GradeHolder>() {

    inner class GradeHolder(view: View) : RecyclerView.ViewHolder(view) {
        val subject: TextView = view.findViewById(R.id.subject)
        val listCount: TextView = view.findViewById(R.id.listCount)
        val gradeAvg: TextView = view.findViewById(R.id.gradeAvg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.e2_grade_list, parent, false)
        return GradeHolder(view)
    }

    override fun onBindViewHolder(holder: GradeHolder, position: Int) {
        val grade = grades[position]
        holder.subject.text = grade.subject
        holder.listCount.text = "Number of tasks: ${grade.listNumber}"
        holder.gradeAvg.text = "Average: ${grade.avgGrade}"
    }

    override fun getItemCount(): Int = grades.size

    // Metoda do aktualizacji danych
    fun updateData(newGrades: List<Grade>) {
        grades = newGrades
        notifyDataSetChanged()
    }
}