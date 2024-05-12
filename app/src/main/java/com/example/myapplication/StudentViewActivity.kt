package com.example.myapplication



import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StudentViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_view)

        val firstNameTextView = findViewById<TextView>(R.id.textViewFirstName)
        val lastNameTextView = findViewById<TextView>(R.id.textViewLastName)
        val emailTextView = findViewById<TextView>(R.id.textViewEmail)
        val phoneTextView = findViewById<TextView>(R.id.textViewPhone)
        val rollNumberTextView = findViewById<TextView>(R.id.textViewRollNumber)
        val courseTextView = findViewById<TextView>(R.id.textViewCourse)

        // Retrieve student details from intent extras
        val firstName = intent.getStringExtra("FIRST_NAME")
        val lastName = intent.getStringExtra("LAST_NAME")
        val email = intent.getStringExtra("EMAIL")
        val phone = intent.getStringExtra("PHONE")
        val rollNumber = intent.getStringExtra("ROLL_NUMBER")
        val course = intent.getStringExtra("COURSE")

        // Set student details to TextViews
        firstNameTextView.text = "First Name: $firstName"
        lastNameTextView.text = "Last Name: $lastName"
        emailTextView.text = "Email: $email"
        phoneTextView.text = "Phone: $phone"
        rollNumberTextView.text = "Roll Number: $rollNumber"
        courseTextView.text = "Course: $course"
    }
}

