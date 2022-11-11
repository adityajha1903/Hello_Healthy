package com.example.adi.hellohealthy

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adi.hellohealthy.databinding.HistoryItemViewHolderNewBinding

class HistoryRecyclerAdapter(
    private val historyList: ArrayList<HistoryEntity>,
    private val deleteListener: (date: String) -> Unit,
    private val showExercisesList: (exerciseArrStr: String) -> Unit
) : RecyclerView.Adapter<HistoryRecyclerAdapter.ViewHolderHistory>(){

    class ViewHolderHistory(binding: HistoryItemViewHolderNewBinding): RecyclerView.ViewHolder(binding.root) {
        val setName = binding.setName
        val dateAndTime = binding.setDateAndTime
        val deleteBtn = binding.iBDeleteSet
        val marginTv = binding.tVToGiveMargin
        val llGetList = binding.llGetList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderHistory {
        return ViewHolderHistory(HistoryItemViewHolderNewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolderHistory, position: Int) {
        if (position == historyList.size - 1) {
            holder.marginTv.visibility = View.VISIBLE
        } else {
            holder.marginTv.visibility = View.GONE
        }
        val set = historyList[position]
        holder.setName.text = set.name
        holder.dateAndTime.text = "on ${set.date.substring(0, 10)} at ${set.date.substring(11, set.date.length)}"
        holder.deleteBtn.setOnClickListener {
            deleteListener.invoke(set.date)
        }
        holder.llGetList.setOnClickListener {
            showExercisesList.invoke(set.exerciseListString)
        }
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

}