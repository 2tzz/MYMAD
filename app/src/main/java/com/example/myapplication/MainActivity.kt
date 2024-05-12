package com.example.myapplication


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var buttonAddStudent: Button
    private lateinit var buttonViewStudent: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize buttons
        buttonAddStudent = findViewById(R.id.buttonAddStudent)
        buttonViewStudent = findViewById(R.id.buttonViewStudents)

        // Set click listeners
        buttonAddStudent.setOnClickListener {
            val intent = Intent(this, StudentDetailsActivity::class.java)
            startActivity(intent)
        }

        buttonViewStudent.setOnClickListener {
            val intent = Intent(this, StudentListActivity::class.java)
            startActivity(intent)
        }
    }
}
