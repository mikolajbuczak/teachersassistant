package com.example.teacherassistant.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.model.entity.StudentCourse

@Dao
interface StudentCourseDao {
    @Insert
    suspend fun insert(studentCourse:StudentCourse)

    @Delete
    suspend fun delete(studentCourse:StudentCourse)

    @Query("SELECT * FROM student_course_table")
    fun allStudentCourse():LiveData<List<StudentCourse>>
}