package com.example.teacherassistant.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.model.entity.Grade

class GradeAdapter(var grades: LiveData<List<Grade>>, private val viewmodel: GradeViewModel): RecyclerView.Adapter<GradeAdapter.GradeHolder>() {
    inner class GradeHolder(val view: View): RecyclerView.ViewHolder(view) {
        var textViewGrade = view.findViewById<TextView>(R.id.grade)!!
        var textViewNote = view.findViewById<TextView>(R.id.note)!!
        var ButtonDeleteGrade = view.findViewById<Button>(R.id.ButtonDeleteGrade)!!
    }

    override  fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.one_grade, parent, false) as View
        return GradeHolder(view)
    }

    override fun getItemCount(): Int {
        return grades.value?.size?:0
    }

    override fun onBindViewHolder(holder: GradeHolder, position: Int) {
        holder.textViewGrade.text = grades.value?.get(position)?.grade.toString()
        holder.textViewNote.text = grades.value?.get(position)?.note.toString()
        holder.ButtonDeleteGrade.setOnClickListener{ viewmodel.deleteGrade(grades.value?.get(position)) }
    }
}