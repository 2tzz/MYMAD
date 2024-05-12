package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.database.Student
import com.google.android.material.snackbar.Snackbar
//import com.example.myapplication.database.MyApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
//import kotlinx.android.synthetic.main.activity_login.*


class StudentDetailsActivity : AppCompatActivity() {
    private lateinit var firstname: EditText
    private lateinit var lastname: EditText
    private lateinit var email: EditText
    private lateinit var phone: EditText
    private lateinit var rollNumber: EditText
    private lateinit var course: EditText
    private lateinit var saveButton: Button
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        firstname = findViewById(R.id.firstname)
        lastname = findViewById(R.id.lastName)
        email = findViewById(R.id.email)
        phone = findViewById(R.id.phone)
        rollNumber = findViewById(R.id.rollNumber)
        course = findViewById(R.id.course)
        saveButton = findViewById(R.id.button10)



        saveButton.setOnClickListener {
            if (validateForm()) {
                saveStudent()
            }
        }
    }
    fun View.showSnackbar(message: String) {
        Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun validateCredentials(username: String, password: String): Boolean {
        if (username.isEmpty() || password.isEmpty()) {
            findViewById<View>(android.R.id.content).showSnackbar("Username and password are required")
            return false
        }

        // You can add more complex validation logic here if needed
        return true
    }


    private fun validateForm(): Boolean {
        val firstName = firstname.text.toString().trim()
        val lastName = lastname.text.toString().trim()
        val emailStr = email.text.toString().trim()
        val phoneStr = phone.text.toString().trim()
        val rollNum = rollNumber.text.toString().trim()
        val courseStr = course.text.toString().trim()

        if (firstName.isEmpty() || lastName.isEmpty() || emailStr.isEmpty() || phoneStr.isEmpty() || rollNum.isEmpty() || courseStr.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            return false
        }

        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+"
        if (!emailStr.matches(emailPattern.toRegex())) {
            Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show()
            return false
        }

        if (phoneStr.length != 10 || !phoneStr.matches("[0-9]+".toRegex())) {
            Toast.makeText(this, "Invalid phone number", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun saveStudent() {
        val fname = firstname.text.toString()
        val lname = lastname.text.toString()
        val emailStr = email.text.toString()
        val phoneStr = phone.text.toString()
        val rollNum = rollNumber.text.toString()
        val courseStr = course.text.toString()

        val student = Student(firstName = fname, lastName = lname, email = emailStr, phone = phoneStr, rollNumber = rollNum, course = courseStr)

        CoroutineScope(Dispatchers.IO).launch {
            MyApplication.database.studentDao().insertStudent(student)
        }

        Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_LONG).show()
        clearFields()
    }

    private fun clearFields() {
        firstname.text.clear()
        lastname.text.clear()
        email.text.clear()
        phone.text.clear()
        rollNumber.text.clear()
        course.text.clear()
    }
}
