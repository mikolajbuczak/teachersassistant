package com.example.teacherassistant.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.model.entity.Grade

@Dao
interface GradeDao {
    @Insert
    suspend fun insert(grade:Grade)

    @Delete
    suspend fun delete(grade:Grade)

    @Query("SELECT * FROM grade_table")
    fun allGrade():LiveData<List<Grade>>
}