package com.example.teacherassistant.model.repositories

import androidx.lifecycle.LiveData
import com.example.teacherassistant.model.entity.StudentCourse
import com.example.teacherassistant.model.dao.StudentCourseDao

class StudentCourseRepository(private val studentCourseDao: StudentCourseDao) {
    val studentCourses: LiveData<List<StudentCourse>> = studentCourseDao.allStudentCourse()

    suspend fun add(student:StudentCourse) {
        studentCourseDao.insert(student)
    }

    suspend fun delete(student:StudentCourse) {
        studentCourseDao.delete(student)
    }
}