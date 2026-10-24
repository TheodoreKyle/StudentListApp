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

        addStudentRecord("Theodore Kyle Choa", "3ITB")
        addStudentRecord("Ron Christian Evaristo", "3ITF")
        addStudentRecord("Joaquin Elcana", "3ITE")

        rvStudents.layoutManager = LinearLayoutManager(this)
        studentAdapter = StudentAdapter(studentList)

        btnAddStudent.setOnClickListener {
            showAddStudentDialog()
        }
    }

    private fun addStudentRecord(
        name: String, section: String
    ) {
        val student = HashMap<String, String>()
        student["name"] = name
        student["section"] = section
    }

    private fun showAddStudentDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_student, null)
        val etStudentName = dialogView.findViewById<EditText>(R.id.etStudentName)
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
                val section = etStudentSection.text.toString().trim()

                if (name.isEmpty() || section.isEmpty()) {
                    Toast.makeText(this, "Please enter name and section.", Toast.LENGTH_SHORT).show()
                } else {
                    addStudentRecord(name, section)
                    studentAdapter.notifyItemInserted(studentList.size - 1)
                    dialog.dismiss()
                }
            }
        }
        dialog.show()
    }
}