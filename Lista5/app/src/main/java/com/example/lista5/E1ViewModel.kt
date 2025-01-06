package com.example.lista5.ui.theme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

// ViewModel for managing and providing data to the UI in the E1 screen
class E1ViewModel : ViewModel() {

    data class Tasks(
        val subject: String,
        val taskName: String,
        val listCount: Int,
        val totalPoints: Int,
        val grade: Double,
        val numOfExercises: Int

    )

    // generate task lists with random values
    fun generateTaskLists(): List<Tasks> {
        val subjects = listOf("Matematyka", "PUM", "Fizyka", "Elektronika", "Algorytmy")
        val listCounters = mutableMapOf<String, Int>() // Tracks count of lists for each subject
        val taskLists = mutableListOf<Tasks>()

        for (i in 1..20) {
            val subject = subjects.random()

            // Increment list count for the subject or initialize from 1
            val taskNumber = listCounters.getOrDefault(subject, 0) + 1
            listCounters[subject] = taskNumber

            // Generate random values for task count, total points, and grade
            val taskCount = Random.nextInt(1, 11)
            val totalPoints = taskCount * Random.nextInt(1, 11)
            val grade = Random.nextDouble(3.0, 5.1).let {
                (it * 2).toInt() / 2.0
            }
            val num = Random.nextInt(1,11)

            // Create a new task list entry
            val taskList = Tasks(
                subject = subject,
                taskName = "List $taskNumber", // Sequential list number
                listCount = taskCount,        // Number of tasks
                totalPoints = totalPoints,
                grade = grade,
                numOfExercises = num
            )

            taskLists.add(taskList)
        }
        return taskLists
    }


    // LiveData holding the list of tasks
    val _taskLists: MutableLiveData<List<Tasks>> = MutableLiveData()

    init {
        // Initialize the data only once
        if (_taskLists.value == null) {
            _taskLists.value = generateTaskLists()
        }
    }

    // expose the list of tasks as immutable LiveData to the UI
    fun getTaskLists(): LiveData<List<Tasks>> = _taskLists

    // Function to get the summary of tasks per subject
    fun getSubjectSummary(): Map<String, Pair<Int, Double>> {
        val taskLists = _taskLists.value ?: return emptyMap()

        // Group tasks by subject and calculate the total number of lists and average grade
        return taskLists.groupBy { it.subject }
            .mapValues { (_, tasks) ->
                val totalLists = tasks.size
                val averageGrade = tasks.map { it.grade }.average()
                totalLists to averageGrade
            }
    }
}