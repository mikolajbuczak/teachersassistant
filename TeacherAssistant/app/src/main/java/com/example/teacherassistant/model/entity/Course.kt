package com.example.teacherassistant.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course_table")
data class Course(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var name: String)