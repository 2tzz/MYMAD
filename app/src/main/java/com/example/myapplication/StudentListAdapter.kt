package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.database.Student

class StudentListAdapter(private val onDeleteClickListener: (Student) -> Unit) : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {

    private var students: List<Student> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.bind(student)
        holder.itemView.findViewById<Button>(R.id.buttonDelete).setOnClickListener {
            onDeleteClickListener(student)
        }
    }

    override fun getItemCount(): Int {
        return students.size
    }

    fun setData(students: List<Student>) {
        this.students = students
        notifyDataSetChanged()
    }

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(student: Student) {
            itemView.findViewById<TextView>(R.id.textName).text = "${student.firstName} ${student.lastName}"
            itemView.findViewById<TextView>(R.id.textEmail).text = "Email: ${student.email}"
            itemView.findViewById<TextView>(R.id.textPhone).text = "Phone: ${student.phone}"
            itemView.findViewById<TextView>(R.id.textrollNumber).text = "Roll Number: ${student.rollNumber}"
            itemView.findViewById<TextView>(R.id.textViewCourse).text = "Course: ${student.course}"
        }
    }
}