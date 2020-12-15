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
import com.example.teacherassistant.model.entity.Student

class StudentAdapter(val students: LiveData<List<Student>>, private val viewmodel: StudentListViewModel) : RecyclerView.Adapter<StudentAdapter.StudentHolder>() {
    inner class StudentHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var textViewStudentName = view.findViewById<TextView>(R.id.studentName)
        var ButtonDeleteStudent = view.findViewById<Button>(R.id.ButtonDeleteStudent)!!
    }

    override  fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.one_student, parent, false) as View
        return StudentHolder(view)
    }

    override fun getItemCount(): Int {
        return students.value?.size?:0
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        holder.textViewStudentName.text = students.value?.get(position)?.name.toString()

        holder.itemView.setOnClickListener {
            viewmodel.setCurrentStudent(students.value?.get(position))
            holder.itemView.findNavController().navigate(R.id.action_studentsList_to_studentInfo)
        }
        holder.ButtonDeleteStudent.setOnClickListener{ viewmodel.deleteStudent(students.value?.get(position)) }
    }
}