package com.example.teacherassistant.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.teacherassistant.R
import kotlinx.android.synthetic.main.fragment_edit_student.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class EditStudent : Fragment() {
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
        return inflater.inflate(R.layout.fragment_edit_student, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        BackButtonEditStudent.setOnClickListener { x -> x.findNavController().navigate(R.id.action_editStudent_to_student) }
        SaveButtonEditStudent.setOnClickListener { x -> x.findNavController().navigate(R.id.action_editStudent_to_student) }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                EditStudent().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}