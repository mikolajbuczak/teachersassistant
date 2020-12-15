package com.example.teacherassistant.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.teacherassistant.R
import com.example.teacherassistant.utils.Utils
import com.example.teacherassistant.view.MainActivity
import com.example.teacherassistant.viewmodel.CoursesListViewModel
import kotlinx.android.synthetic.main.fragment_add_course.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private  lateinit var viewmodel: CoursesListViewModel

class AddCourse : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

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
        viewmodel = ViewModelProvider(requireActivity()).get(CoursesListViewModel::class.java)
        return inflater.inflate(R.layout.fragment_add_course, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        BackButtonAddCourse.setOnClickListener { x -> x.findNavController().navigate(R.id.action_addCourse_to_coursesList) }
        SaveButtonAddCourse.setOnClickListener { x ->
            viewmodel.addCourse(addCourseName.text.toString())
            x.findNavController().navigate(R.id.action_addCourse_to_coursesList) }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddCourse().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}