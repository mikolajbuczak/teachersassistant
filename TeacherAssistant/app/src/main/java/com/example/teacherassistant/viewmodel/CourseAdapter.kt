package com.example.teacherassistant.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.model.entity.Course

class CourseAdapter(var courses: LiveData<List<Course>>, private val viewmodel: CoursesListViewModel): RecyclerView.Adapter<CourseAdapter.CourseHolder>() {
    inner class CourseHolder(val view: View):RecyclerView.ViewHolder(view) {
        var textViewCourseName = view.findViewById<TextView>(R.id.courseName)!!
        var ButtonDeleteCourse = view.findViewById<Button>(R.id.ButtonDeleteCourse)!!
    }

    override  fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.one_course, parent, false) as View
        return CourseHolder(view)
    }

    override fun getItemCount(): Int {
        return courses.value?.size?:0
    }

    override fun onBindViewHolder(holder: CourseHolder, position: Int) {
        holder.textViewCourseName.text = courses.value?.get(position)?.name.toString()

        holder.itemView.setOnClickListener {
            viewmodel.setCurrentCourse(courses.value?.get(position))
            holder.itemView.findNavController().navigate(R.id.action_coursesList_to_studentsList)
        }
        holder.ButtonDeleteCourse.setOnClickListener{ viewmodel.deleteCourse(courses.value?.get(position)) }
    }
}