package com.example.adi.hellohealthy.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.adi.hellohealthy.others.Exercise
import com.example.adi.hellohealthy.R
import com.example.adi.hellohealthy.databinding.ItemExerciseLayoutBinding

class ExerciseStatusAdapter(private val exerciseList: ArrayList<Exercise>): RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolderExerciseStatus>() {

    class ViewHolderExerciseStatus(binding: ItemExerciseLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        val tvExerciseNumTv = binding.rcEachEx
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderExerciseStatus {
        return ViewHolderExerciseStatus(ItemExerciseLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolderExerciseStatus, position: Int) {
        val exercise = exerciseList[position]
        holder.tvExerciseNumTv.text = (position + 1).toString()

        when {
            exercise.getIsCompleted() -> {
                holder.tvExerciseNumTv.background = ContextCompat.getDrawable(holder.itemView.context,
                    R.drawable.ic_each_rv_ex_completed_green_bg
                )
                holder.tvExerciseNumTv.setTextColor(Color.WHITE)
            }
            exercise.getIsSelected() -> {
                holder.tvExerciseNumTv.background = ContextCompat.getDrawable(holder.itemView.context,
                    R.drawable.ic_each_rv_ex_selected_white_bg
                )
                holder.tvExerciseNumTv.setTextColor(Color.WHITE)
            }
            else -> {
                holder.tvExerciseNumTv.background = ContextCompat.getDrawable(holder.itemView.context,
                    R.drawable.ic_each_rv_ex_grey_bg
                )
                holder.tvExerciseNumTv.setTextColor(Color.BLACK)
            }
        }
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

}