package com.example.teacherassistant.model.repositories

import androidx.lifecycle.LiveData
import com.example.teacherassistant.model.entity.Course
import com.example.teacherassistant.model.dao.CourseDao
import com.example.teacherassistant.model.entity.Student

class CourseRepository(private val courseDao: CourseDao) {
    val courses: LiveData<List<Course>> = courseDao.allCourse()

    suspend fun add(course:Course) {
        courseDao.insert(course)
    }

    suspend fun delete(course:Course) {
        courseDao.delete(course)
    }

    suspend fun deleteAt(id: Int) {
        val course: Course? = courseDao.getCourseAt(id)
        if (course!=null)
            delete(course)
    }

    companion object {
        fun getStudentsFromCourse(id: Int): List<Student> {
            return mutableListOf<Student>()
        }
    }
}