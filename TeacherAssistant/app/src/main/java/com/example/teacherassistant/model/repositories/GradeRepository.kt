package com.example.teacherassistant.model.repositories

import androidx.lifecycle.LiveData
import com.example.teacherassistant.model.entity.Grade
import com.example.teacherassistant.model.dao.GradeDao

class GradeRepository(private val gradeDao: GradeDao) {
    val grades: LiveData<List<Grade>> = gradeDao.allGrade()

    suspend fun add(grade:Grade) {
        gradeDao.insert(grade)
    }

    suspend fun delete(grade:Grade) {
        gradeDao.delete(grade)
    }
}