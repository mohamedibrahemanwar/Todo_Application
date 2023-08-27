package com.example.todo.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.todo.ui.home.tabs.tasks_list.ListFragment

import com.example.todo.ui.home.tabs.SettingsFragment

import com.example.todo.ui.home.tabs.AddTaskFragment
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityHomeBinding
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {
    lateinit var viewBinding : ActivityHomeBinding
    var taskListFragmentRef : ListFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.bottomNAV.setOnItemSelectedListener (
            object : NavigationBarView.OnItemSelectedListener {
                override fun onNavigationItemSelected(item: MenuItem): Boolean {
                    when (item.itemId) {
                        R.id.list_item -> {
                            taskListFragmentRef = ListFragment()
                            showTab(taskListFragmentRef!!)
                        }

                        R.id.settings_item -> {
                            showTab(SettingsFragment())
                        }
                    }
                    return true
                }

        }
            )
        viewBinding.addTask.setOnClickListener {
            showAddTastBottomSheet()
        }
        viewBinding.bottomNAV.selectedItemId = R.id.list_item
    }

    private fun showAddTastBottomSheet() {
        val addTask = AddTaskFragment()
        addTask.onTaskAddedListener = AddTaskFragment.OnTaskAddedListener{
            Snackbar.make(viewBinding.root,"Task Added", Snackbar.LENGTH_SHORT).show()
            //notify tasks list fragment
            taskListFragmentRef?.loadDate()
        }
        addTask.show(supportFragmentManager,"")
    }

    private fun showTab(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.fade_in,R.anim.fade_out)
            .replace(R.id.fragment_container,fragment)
            .commit()
    }
}