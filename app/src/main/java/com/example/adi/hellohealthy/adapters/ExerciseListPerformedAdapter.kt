package com.example.adi.hellohealthy.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adi.hellohealthy.others.Exercise
import com.example.adi.hellohealthy.databinding.ExerciseNameAndImgViewHolderForEachSetBinding

class ExerciseListPerformedAdapter(private val exerciseList: ArrayList<Exercise>): RecyclerView.Adapter<ExerciseListPerformedAdapter.ViewHolderExerciseListPerformed>() {

    class ViewHolderExerciseListPerformed(binding: ExerciseNameAndImgViewHolderForEachSetBinding): RecyclerView.ViewHolder(binding.root) {
        val exerciseNumber = binding.exerciseNumber
        val exerciseName = binding.exerciseName
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderExerciseListPerformed {
        return ViewHolderExerciseListPerformed(ExerciseNameAndImgViewHolderForEachSetBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolderExerciseListPerformed, position: Int) {
        holder.exerciseNumber.text = (position + 1).toString()
        holder.exerciseName.text = exerciseList[position].getName()
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }


}