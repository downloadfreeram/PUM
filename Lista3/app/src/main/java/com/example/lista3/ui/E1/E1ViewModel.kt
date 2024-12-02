package com.example.lista3.ui.E1

import Grade
import TaskList
import Tasks
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import generateTaskLists
import kotlin.math.roundToInt
import kotlin.random.Random

class E1ViewModel : ViewModel() {

    private val _taskLists: MutableLiveData<List<Tasks>> = MutableLiveData()
    private val _selectedTaskDetails: MutableLiveData<List<TaskList>> = MutableLiveData()

    init {
        // generate data at the start of the app
        _taskLists.value = generateTaskLists()
    }

    fun getTaskLists(): LiveData<List<Tasks>> = _taskLists

    fun getSelectedTaskDetails(): LiveData<List<TaskList>> = _selectedTaskDetails

    fun getSubjectSummaries(): LiveData<List<Grade>> {
        val result = MutableLiveData<List<Grade>>()
        _taskLists.observeForever { taskLists ->
            val summaries = taskLists.groupBy { it.subject }.map { entry ->
                val subject = entry.key
                val lists = entry.value
                val averageGrade = lists.map { it.grade }.average()
                val roundedGrade = (averageGrade * 10).roundToInt() / 10.0
                val taskListCount = lists.size
                Grade(subject, taskListCount, roundedGrade)
            }
            result.value = summaries
        }
        return result
    }
    fun selectTask(task: Tasks) {
        val taskDetails = List(task.listCount) { index ->
            TaskList(
                description = "Task ${index + 1}: Lorem Ipsum",
                points = Random.nextInt(1, 11)
            )
        }
        _selectedTaskDetails.value = taskDetails
    }
}