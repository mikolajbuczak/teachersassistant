package com.example.teacherassistant.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.model.MainDatabase
import com.example.teacherassistant.model.entity.Grade
import com.example.teacherassistant.model.repositories.GradeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GradeViewModel(application: Application) : AndroidViewModel(application) {
    val grades: LiveData<List<Grade>>
    private val repository: GradeRepository

    init {
        val gradeDao = MainDatabase.getDatabase(application).gradeDao()
        repository = GradeRepository(gradeDao)
        grades = repository.grades
    }

    fun addGrade(id_s: Int, id_c: Int, gradeValue: Double, noteValue: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(Grade(id = 0, student_id = id_s, course_id = id_c, grade = gradeValue, note = noteValue))
        }
    }

    fun deleteGrade(grade: Grade?) {
        viewModelScope.launch(Dispatchers.IO) {
            if(grade != null)
                repository.delete(grade)
        }
    }
}