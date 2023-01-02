package com.example.adi.hellohealthy.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adi.hellohealthy.others.Exercise
import com.example.adi.hellohealthy.R
import com.example.adi.hellohealthy.databinding.SerchViewViewHolderBinding

class ExerciseSetModifierAdapter(
    private val allExercisesList: ArrayList<Exercise>,
    private val selectedExerciseList: ArrayList<Exercise>,
    private var filterList: ArrayList<Exercise>,
    private val addOrRemoveListener: (id: Int, contains: Boolean) -> Unit):
    RecyclerView.Adapter<ExerciseSetModifierAdapter.ViewHolderExerciseSetModifier>()
{

    class ViewHolderExerciseSetModifier(binding: SerchViewViewHolderBinding): RecyclerView.ViewHolder(binding.root) {
        val exName = binding.exerciseName
        val addOrRemoveBtn = binding.addOrRemoveIB
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setFilteredList(filterList: ArrayList<Exercise>) {
        this.filterList = filterList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder (
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderExerciseSetModifier {
        return ViewHolderExerciseSetModifier(SerchViewViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderExerciseSetModifier, position: Int) {
        val exercise : Exercise = if (filterList.isNotEmpty()) {
            filterList[position]
        } else {
            allExercisesList[position]
        }
        holder.exName.text = exercise.getName()
        var contains = selectedExerciseList.contains(exercise)
        if (contains) {
            holder.addOrRemoveBtn.setImageResource(R.drawable.ic_remove)
        } else {
            holder.addOrRemoveBtn.setImageResource(R.drawable.ic_add)
        }
        holder.addOrRemoveBtn.setOnClickListener {
            addOrRemoveListener.invoke(exercise.getId(), contains)
            if ((contains && selectedExerciseList.size > 5) || (!contains && selectedExerciseList.size < 15)) {
                if (contains) {
                    if (filterList.isNotEmpty()) {
                        selectedExerciseList.remove(filterList[position])
                    } else {
                        selectedExerciseList.remove(allExercisesList[position])
                    }
                    holder.addOrRemoveBtn.setImageResource(R.drawable.ic_add)
                } else {
                    if (filterList.isNotEmpty()) {
                        selectedExerciseList.add(filterList[position])
                    } else {
                        selectedExerciseList.add(allExercisesList[position])
                    }
                    holder.addOrRemoveBtn.setImageResource(R.drawable.ic_remove)
                }
                contains = !contains
            }
        }
    }

    override fun getItemCount(): Int {
        return if (filterList.isNotEmpty()) {
            filterList.size
        } else {
            allExercisesList.size
        }
    }
}