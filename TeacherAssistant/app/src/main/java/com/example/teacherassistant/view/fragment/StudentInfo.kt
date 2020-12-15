package com.example.teacherassistant.view.fragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teacherassistant.R
import com.example.teacherassistant.utils.Utils
import com.example.teacherassistant.view.MainActivity
import com.example.teacherassistant.viewmodel.*
import kotlinx.android.synthetic.main.fragment_studentinfo.*
import kotlinx.android.synthetic.main.fragment_students_list.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class StudentInfo : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var viewModelStudents: StudentListViewModel
    private lateinit var viewModelCourse: CoursesListViewModel
    //private lateinit var viewModelGrade: GradeViewModel
    private lateinit var viewManager: LinearLayoutManager
    private lateinit var gradeAdapter: GradeAdapter

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
        viewModelStudents = ViewModelProvider(requireActivity()).get(StudentListViewModel::class.java)
        viewModelCourse = ViewModelProvider(requireActivity()).get(CoursesListViewModel::class.java)
        viewManager = LinearLayoutManager(requireContext())
        viewModelStudents.getGrades()

        viewModelStudents.currentStudent.observe(viewLifecycleOwner, Observer {
            if(it != null)
                studentInfoStudentName.text = it.name })

        viewModelCourse.currentCourse.observe(viewLifecycleOwner, Observer {
            if(it != null)
                studentInfoCourseName.text = it.name })

        //gradeAdapter = GradeAdapter(viewModelGrade.grades, viewModelGrade)


//        viewModelStudents.currentStudentGrades.observe(viewLifecycleOwner, Observer {
//            gradeAdapter.notifyDataSetChanged()
//        })

        return inflater.inflate(R.layout.fragment_studentinfo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        BackButtonStudentInfo.setOnClickListener { x -> x.findNavController().navigate(R.id.action_student_to_studentsList) }
        EditStudentButton.setOnClickListener { x -> x.findNavController().navigate(R.id.action_student_to_editStudent) }

//        gradesRecyclerView.apply {
//            adapter = gradeAdapter
//            layoutManager = viewManager
//        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StudentInfo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}