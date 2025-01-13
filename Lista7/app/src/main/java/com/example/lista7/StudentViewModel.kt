package com.example.lista7

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StudentViewModel : ViewModel() {
    data class Student(val name:String, val surname:String, val indexNumber:Int, val avgGrades:Double, val year:Int)

    private val _students = MutableLiveData<List<Student>>()
    val students: LiveData<List<Student>> get() = _students

    init {
        generateData()
    }

    private fun generateData() {
        val Students = listOf(
            Student("Jan", "Kowalski", 303030, 3.8, 2),
            Student("Andrzej", "Nowak", 313131, 3.5, 3),
            Student("Robert", "Kubica", 323232, 4.0, 1),
            Student("Grzegorz", "Floryda", 333333, 3.2, 2),
            Student("Michał", "Michałowski", 343434, 3.9, 4),
            Student("Michał", "Michałowski", 343434, 3.9, 4),
            Student("Paweł", "Pawłowski", 353535, 4.2, 1),
            Student("Marek", "Markowski", 363636, 4.3, 2),
            Student("Stefan", "Stefański", 373737, 3.1, 3),
            Student("Piotr", "Piotrowski", 383838, 4.0, 1),
            Student("Kamil", "Labudda", 393939, 3.8, 1),
            Student("Krzysztof", "Krzysztofczyk", 310013, 3.3, 2),
        )
        _students.value = Students
    }

    fun getStudentByIndex(indexNumber: Int): Student? {
        return _students.value?.find { it.indexNumber == indexNumber }
    }
}
