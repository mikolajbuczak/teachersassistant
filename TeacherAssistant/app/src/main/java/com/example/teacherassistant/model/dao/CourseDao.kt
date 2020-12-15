package com.example.teacherassistant.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.model.entity.Course

@Dao
interface CourseDao {
    @Insert
    suspend fun insert(course: Course)

    @Delete
    suspend fun delete(course:Course)

    @Query("Select * from course_table")
    fun allCourse():LiveData<List<Course>>

    @Query("SELECT * FROM course_table WHERE id = :ID")
    fun getCourseAt(ID: Int): Course
}