package com.example.teacherassistant.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.model.MainDatabase
import com.example.teacherassistant.model.entity.Course
import com.example.teacherassistant.model.entity.Grade
import com.example.teacherassistant.model.entity.Student
import com.example.teacherassistant.model.repositories.CourseRepository
import com.example.teacherassistant.model.repositories.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentListViewModel(application: Application) : AndroidViewModel(application) {
    val students: LiveData<List<Student>>
    private val repository: StudentRepository

    private var _currentStudentGrades: MutableLiveData<List<Grade>> = MutableLiveData()
    val currentStudentGrades: LiveData<List<Grade>>
        get()=_currentStudentGrades

    private var _currentStudent: MutableLiveData<Student> = MutableLiveData()
    val currentStudent: LiveData<Student>
        get() = _currentStudent

    init {
        val studentDao = MainDatabase.getDatabase(application).studentDao()
        repository = StudentRepository(studentDao)
        students = repository.students
    }

    fun addStudent(studentName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(Student(id = 0, name = studentName))
        }
    }

    fun setCurrentStudent(student: Student?) {
        if(student != null)
            _currentStudent.value = student
    }

    fun deleteStudent(student: Student?) {
        viewModelScope.launch(Dispatchers.IO) {
            if(student != null)
                repository.delete(student)
        }
    }

    fun getGrades() {
        viewModelScope.launch {
            if(currentStudent.value!=null) {
                _currentStudentGrades.value =
                        StudentRepository.getGradesFromStudent(currentStudent.value!!.id)
            }
            else
                _currentStudentGrades.value= listOf()
        }
    }
}