package com.example.lista3.ui.E1

import Grade
import Tasks
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import generateTaskLists
import kotlin.math.roundToInt

class E1ViewModel : ViewModel() {

    private val _taskLists: MutableLiveData<List<Tasks>> = MutableLiveData()

    init {
        // generate data at the start of the app
        _taskLists.value = generateTaskLists()
    }

    fun getTaskLists(): LiveData<List<Tasks>> = _taskLists

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
}