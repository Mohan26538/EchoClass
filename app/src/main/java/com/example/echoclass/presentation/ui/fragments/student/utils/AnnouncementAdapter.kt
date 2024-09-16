package com.example.echoclass.presentation.ui.fragments.student.utils

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.graphics.ColorUtils
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.echoclass.databinding.AnnouncementItemsBinding
import com.example.echoclass.domain.model.Announcement
import com.example.echoclass.domain.model.Course
import com.example.echoclass.domain.model.Courses
import kotlin.random.Random

class AnnouncementViewHolder(
    private val announcementBinding: AnnouncementItemsBinding
): ViewHolder(announcementBinding.root){
    fun bindData(announcement: Announcement){
        announcementBinding.tvAnnouncementName.text = announcement.name
        announcementBinding.tvAnnouncementDate.text = announcement.date
        announcementBinding.tvAnnouncementTime.text = announcement.time
        announcementBinding.tvAnnouncementPlace.text = announcement.place


    }
}

class AnnouncementAdapter(
    private val listOfAnnouncement: List<Announcement>
): Adapter<AnnouncementViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementViewHolder {
        val binding = AnnouncementItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AnnouncementViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listOfAnnouncement.size
    }

    override fun onBindViewHolder(holder: AnnouncementViewHolder, position: Int) {
        holder.bindData(listOfAnnouncement[position])
    }




}