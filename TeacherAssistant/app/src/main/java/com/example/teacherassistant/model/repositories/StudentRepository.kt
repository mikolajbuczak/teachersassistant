package com.example.teacherassistant.model.repositories

import androidx.lifecycle.LiveData
import com.example.teacherassistant.model.entity.Student
import com.example.teacherassistant.model.dao.StudentDao
import com.example.teacherassistant.model.entity.Grade

class StudentRepository(private val studentDao: StudentDao) {
    val students: LiveData<List<Student>> = studentDao.allStudent()

    suspend fun add(student:Student) {
        studentDao.insert(student)
    }

    suspend fun delete(student:Student) {
        studentDao.delete(student)
    }

    suspend fun deleteAt(id: Int) {
        val student: Student? = studentDao.getStudentAt(id)
        if (student!=null)
            delete(student)
    }

    companion object {
        fun getGradesFromStudent(id: Int): List<Grade> {
            return mutableListOf<Grade>()
        }
    }
}