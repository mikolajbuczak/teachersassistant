package com.example.teacherassistant.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teacherassistant.model.dao.*
import com.example.teacherassistant.model.entity.*

@Database(entities = [Student::class, Course::class, StudentCourse::class, Grade::class], version = 1, exportSchema = false)
abstract class MainDatabase: RoomDatabase() {
    abstract fun studentDao(): StudentDao
    abstract fun courseDao(): CourseDao
    abstract fun studentCourseDao(): StudentCourseDao
    abstract fun gradeDao(): GradeDao

    companion object {
        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getDatabase(context: Context): MainDatabase {
            val tempInstance = INSTANCE

            if(tempInstance != null)
                return tempInstance
            else
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MainDatabase::class.java,
                        "my_database"
                    ).build()
                    INSTANCE = instance
                    return instance
                }
        }
    }
}