package com.example.echoclass.presentation.ui.fragments.student.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.echoclass.databinding.OptionsItemsBinding
import com.example.echoclass.domain.model.Options


class OptionsViewHolder(private val binding:OptionsItemsBinding):ViewHolder(binding.root){
    fun bindData(option: Options){
        binding.imgOption.setImageResource(option.img)
        binding.tvOption.text = option.name
    }
}

class OptionsAdapter(
    private val listOfOptions: List<Options>
): Adapter<OptionsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionsViewHolder {
        val binding = OptionsItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OptionsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listOfOptions.size
    }

    override fun onBindViewHolder(holder: OptionsViewHolder, position: Int) {
        holder.bindData(listOfOptions[position])
    }


}