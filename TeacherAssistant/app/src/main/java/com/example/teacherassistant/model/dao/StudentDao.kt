package com.example.teacherassistant.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.model.entity.Student

@Dao
interface StudentDao {
    @Insert
    suspend fun insert(student: Student)

    @Delete
    suspend fun delete(student:Student)

    @Query("SELECT * FROM student_table")
    fun allStudent():LiveData<List<Student>>

    @Query("SELECT * FROM student_table WHERE id = :ID")
    fun getStudentAt(ID: Int): Student
}