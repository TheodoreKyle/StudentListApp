package com.example.studentlistapp

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val studentList = ArrayList<HashMap<String, String>>()
    private lateinit var studentAdapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvStudents = findViewById<RecyclerView>(R.id.rvStudents)
        val btnAddStudent = findViewById<Button>(R.id.btnAddStudent)
        val btnClear =findViewById<Button>(R.id.btnClear)

        addStudentRecord("Theodore Kyle Choa", "Information Technology", "3ITB")
        addStudentRecord("Ron Christian Evaristo", "Information Technology", "3ITF")
        addStudentRecord("Joaquin Elcana", "Information Technology", "3ITE")

        rvStudents.layoutManager = LinearLayoutManager(this)
        studentAdapter = StudentAdapter(studentList)
        rvStudents.adapter = studentAdapter

        btnAddStudent.setOnClickListener {
            showAddStudentDialog()
        }

        btnClear.setOnClickListener {
            studentList.clear()
            studentAdapter.notifyDataSetChanged()
        }
    }

    private fun addStudentRecord(
        name: String, course: String, section: String
    ) {
        val student = HashMap<String, String>()
        student["name"] = name
        student["course"] = course
        student["section"] = section

        studentList.add(student)
    }

    private fun showAddStudentDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_student, null)
        val etStudentName = dialogView.findViewById<EditText>(R.id.etStudentName)
        val etStudentCourse = dialogView.findViewById<EditText>(R.id.etStudentCourse)
        val etStudentSection = dialogView.findViewById<EditText>(R.id.etStudentSection)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Add Student")
            .setView(dialogView)
            .setPositiveButton("Add", null)
            .setNegativeButton("Cancel", null)
            .create()

        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                val name = etStudentName.text.toString().trim()
                val course = etStudentCourse.text.toString().trim()
                val section = etStudentSection.text.toString().trim()

                if (name.isEmpty() || course.isEmpty() || section.isEmpty()) {
                    Toast.makeText(this, "Please enter name and section.", Toast.LENGTH_SHORT).show()
                } else {
                    addStudentRecord(name, course, section)
                    studentAdapter.notifyItemInserted(studentList.size - 1)
                    dialog.dismiss()
                }
            }
        }
        dialog.show()
    }
}