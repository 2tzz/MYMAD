package com.example.myapplication

import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.database.Student
//import com.example.myapplication.MyApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StudentListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = StudentListAdapter { student ->
            // Handle delete action here
            // For example, show a confirmation dialog and delete the student upon confirmation
            showDeleteConfirmationDialog(student)
        }

        recyclerView.adapter = adapter

        // Fetch data and set it to the adapter
        GlobalScope.launch(Dispatchers.Main) {
            val students = MyApplication.database.studentDao().getAllStudents()
            adapter.setData(students)
        }
    }

    private fun showDeleteConfirmationDialog(student: Student) {
        AlertDialog.Builder(this)
            .setTitle("Delete Student")
            .setMessage("Are you sure you want to delete this student?")
            .setPositiveButton("Yes") { _, _ ->
                // Delete the student from the database
                GlobalScope.launch(Dispatchers.IO) {
                    MyApplication.database.studentDao().deleteStudent(student)
                }

                // Update the UI after deletion
                GlobalScope.launch(Dispatchers.Main) {
                    val students = MyApplication.database.studentDao().getAllStudents()
                    adapter.setData(students)
                }
            }
            .setNegativeButton("No", null)
            .show()
    }
}
