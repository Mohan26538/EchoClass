package com.example.echoclass.presentation.ui.fragments.student.utils

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.echoclass.R
import com.example.echoclass.databinding.CourseItemsBinding
import com.example.echoclass.databinding.OptionsItemsBinding
import com.example.echoclass.domain.model.Course
import com.example.echoclass.domain.model.Courses
import kotlin.random.Random

class CourseViewHolder(
    private val courseBinding:CourseItemsBinding
):ViewHolder(courseBinding.root){
    fun bindData(course: Course){
        val cardView = courseBinding.cardView
        setRandomDarkColorToCardView(cardView)
        courseBinding.tvCourse.text = course.name


    }

    fun setRandomDarkColorToCardView(cardView: CardView) {
        val lightColor = generateRandomLightColor()

        val darkColor = ColorUtils.blendARGB(lightColor, Color.BLACK, 0.5f) // Darken by 50%

        cardView.setCardBackgroundColor(darkColor)
    }

    private fun generateRandomLightColor(): Int {
        val random = Random.Default
        val red = random.nextInt(128, 256)
        val green = random.nextInt(128, 256)
        val blue = random.nextInt(128, 256)
        return Color.rgb(red, green, blue)
    }
}

class CourseAdapter(
    private val listOfCourse: Courses
):Adapter<CourseViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = CourseItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CourseViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listOfCourse.courses.size
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bindData(listOfCourse.courses[position])
    }




}