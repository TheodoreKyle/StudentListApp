package com.example.studentlistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(
    private val students: ArrayList<HashMap<String, String>>
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvStudentName = itemView.findViewById<TextView>(R.id.tvStudentName)
        val tvStudentSection = itemView.findViewById<TextView>(R.id.tvStudentSection)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)

        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.tvStudentName.text = student["name"] ?: ""
        holder.tvStudentSection.text = student["section"] ?: ""
    }

    override fun getItemCount(): Int {
        return students.size
    }
}