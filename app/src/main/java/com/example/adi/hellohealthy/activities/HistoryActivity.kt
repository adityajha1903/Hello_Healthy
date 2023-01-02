package com.example.adi.hellohealthy.activities

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adi.hellohealthy.*
import com.example.adi.hellohealthy.adapters.ExerciseListPerformedAdapter
import com.example.adi.hellohealthy.adapters.HistoryRecyclerAdapter
import com.example.adi.hellohealthy.database.dao.HistoryDao
import com.example.adi.hellohealthy.database.entity.HistoryEntity
import com.example.adi.hellohealthy.databinding.ActivityHistoryBinding
import com.example.adi.hellohealthy.databinding.ExerciseListForEachSetDialogBinding
import com.example.adi.hellohealthy.databinding.HistoryDeleteDiialogBinding
import com.example.adi.hellohealthy.others.Constants
import com.example.adi.hellohealthy.others.HelloHealthyApp
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {

    private var binding: ActivityHistoryBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.historyActivityToolBar)
        supportActionBar?.setTitle(R.string.history)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val historyDao = (application as HelloHealthyApp).historyDb.historyDao()

        if (Build.VERSION.SDK_INT >= 33) {
            onBackInvokedDispatcher.registerOnBackInvokedCallback(
                OnBackInvokedDispatcher.PRIORITY_DEFAULT
            ) {
                finish()
            }
        } else {
            onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    finish()
                }
            })
        }

        binding?.historyActivityToolBar?.setNavigationOnClickListener {
            finish()
        }

        lifecycleScope.launch {
            historyDao.fetchAllSetsInPast().collect{
                val list = ArrayList(it)
                setUpListOfSetsInRecyclerView(list, historyDao)
            }
        }
    }

    private fun setUpListOfSetsInRecyclerView(historyList: ArrayList<HistoryEntity>, historyDao: HistoryDao) {
        if (historyList.isNotEmpty()) {
            val historyAdapter = HistoryRecyclerAdapter(historyList, { deleteDate ->
                deleteSetPerformed(historyDao, deleteDate)
            }, { exerciseArrStr ->
                showExerciseListDialog(exerciseArrStr)
            })
            val lLM = LinearLayoutManager(this)
            lLM.reverseLayout = true
            lLM.scrollToPosition(historyList.size - 1)
            binding?.historyRecyclerView?.layoutManager = lLM
            binding?.historyRecyclerView?.adapter = historyAdapter
            binding?.historyRecyclerView?.visibility = View.VISIBLE
            binding?.tv?.visibility = View.GONE
        } else {
            binding?.historyRecyclerView?.visibility = View.GONE
            binding?.tv?.visibility = View.VISIBLE
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showExerciseListDialog(exerciseArrStr: String) {
        val exerciseList = Constants.getExerciseListForDisplayingOnHistoryDialog(exerciseArrStr)
        val exerciseListAdapter = ExerciseListPerformedAdapter(exerciseList)
        val dialog = Dialog(this)
        val dialogBinding = ExerciseListForEachSetDialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialogBinding.exerciseListRV.layoutManager = LinearLayoutManager(this)
        dialogBinding.exerciseListRV.adapter = exerciseListAdapter
        exerciseListAdapter.notifyDataSetChanged()
        dialog.show()
    }

    private fun deleteSetPerformed(historyDao: HistoryDao, date: String) {
        val dialog = Dialog(this)
        val dialogBinding = HistoryDeleteDiialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCanceledOnTouchOutside(false)
        dialogBinding.yesDeleteBtn.setOnClickListener {
            lifecycleScope.launch {
                historyDao.delete(HistoryEntity(date))
            }
            dialog.dismiss()
        }
        dialogBinding.noDeleteBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}