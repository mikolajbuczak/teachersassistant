package com.example.teacherassistant.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.viewmodel.CoursesListViewModel
import com.example.teacherassistant.viewmodel.StudentAdapter
import kotlinx.android.synthetic.main.fragment_students_list.*
import com.example.teacherassistant.viewmodel.StudentListViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class StudentsList : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var viewModelCourses: CoursesListViewModel
    private lateinit var viewModelStudents: StudentListViewModel
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        viewModelCourses = ViewModelProvider(requireActivity()).get(CoursesListViewModel::class.java)
        viewModelStudents = ViewModelProvider(requireActivity()).get(StudentListViewModel::class.java)
        viewManager = LinearLayoutManager(requireContext())
        viewModelCourses.getStudents()

        viewModelCourses.currentCourse.observe(viewLifecycleOwner, Observer {
            if(it != null)
                studentsCourseName.text = it.name })

        viewModelCourses.currentCourseStudents.observe(viewLifecycleOwner, Observer {
            studentAdapter.notifyDataSetChanged()
        })


        studentAdapter = StudentAdapter(viewModelStudents.students, viewModelStudents)

        viewModelStudents.students.observe(viewLifecycleOwner, Observer { _ ->
            studentAdapter.notifyDataSetChanged()
        })

        return inflater.inflate(R.layout.fragment_students_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        BackButtonStudentsList.setOnClickListener { x -> x.findNavController().navigate(R.id.action_studentsList_to_coursesList) }
        AddStudentButton.setOnClickListener { x -> x.findNavController().navigate(R.id.action_studentsList_to_addStudent) }
        EditCourseButton.setOnClickListener { x -> x.findNavController().navigate(R.id.action_studentsList_to_editCourse) }

        coursesListRecyclerView.apply {
            adapter = studentAdapter
            layoutManager = viewManager
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StudentsList().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}