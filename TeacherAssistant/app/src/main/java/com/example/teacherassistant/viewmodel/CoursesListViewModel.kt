package com.example.teacherassistant.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.model.MainDatabase
import com.example.teacherassistant.model.entity.Course
import com.example.teacherassistant.model.entity.Student
import com.example.teacherassistant.model.repositories.CourseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoursesListViewModel(application: Application) : AndroidViewModel(application) {
    val courses: LiveData<List<Course>>
    private val repository: CourseRepository

    private var _currentCourseStudents: MutableLiveData<List<Student>> = MutableLiveData()
    val currentCourseStudents: LiveData<List<Student>>
        get()=_currentCourseStudents

    private var _currentCourse: MutableLiveData<Course> = MutableLiveData()
    val currentCourse: LiveData<Course>
        get() = _currentCourse

    init {
        val courseDao = MainDatabase.getDatabase(application).courseDao()
        repository = CourseRepository(courseDao)
        courses = repository.courses
    }

    fun addCourse(courseName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(Course(id = 0, name = courseName))
        }
    }

    fun setCurrentCourse(course: Course?) {
        if(course != null)
            _currentCourse.value = course
    }

    fun deleteCourse(course: Course?) {
        viewModelScope.launch(Dispatchers.IO) {
            if(course != null)
                repository.delete(course)
        }
    }

    fun getStudents() {
        viewModelScope.launch {
            if(currentCourse.value!=null) {
                _currentCourseStudents.value =
                        CourseRepository.getStudentsFromCourse(currentCourse.value!!.id)
            }
            else
                _currentCourseStudents.value= listOf()
        }
    }
}