package com.example.myapplication
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (validateCredentials(username, password)) {
                // Perform authentication logic here (for example, call an API for authentication)
                // If authentication is successful, navigate to the next screen
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loginUser() {
        val username = usernameEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        // Validate username and password (you can add your validation logic here)
        if (validateCredentials(username, password)) {
            val intent = Intent(this, StudentDetailsActivity::class.java)
            intent.putExtra("USERNAME", username) // Pass the username to StudentDetailsActivity
            startActivity(intent)
            finish()
        } else {
            // Show an error message or handle invalid credentials
        }
    }

    private fun validateCredentials(username: String, password: String): Boolean {
        val validUsername = "admin"
        val validPassword = "123"

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Username and password are required", Toast.LENGTH_SHORT).show()
            return false
        }

        if (username == validUsername && password == validPassword) {
            // Valid username and password, allow login
            return true
        } else {
            // Invalid username or password, show error message
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            return false
        }
    }

}

