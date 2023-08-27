package com.example.todo.ui.home.tabs

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.todoapp.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {
    lateinit var viewBinding : FragmentSettingsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentSettingsBinding.inflate(inflater,container,false)
       return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val modeItems = arrayOf("Light", "Dark" )
        val adapter = activity?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_item, modeItems)
        }
        viewBinding.spinnerMode.setAdapter(adapter)
    }
}