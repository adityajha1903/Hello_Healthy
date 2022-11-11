package com.example.adi.hellohealthy

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adi.hellohealthy.databinding.ActivityDaySelectorBinding
import com.example.adi.hellohealthy.databinding.ExerciseSetModifierDialogBinding
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

class DaySelectorActivity : AppCompatActivity() {

    private var binding: ActivityDaySelectorBinding? = null

    private var setModifierAdapter : ExerciseSetModifierAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day_selector)

        binding = ActivityDaySelectorBinding.inflate(layoutInflater)
        setContentView(binding?.root)

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

        setSupportActionBar(binding?.exerciseSelectorActivityToolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.exerciseSelectorActivityToolBar?.setNavigationOnClickListener {
            finish()
        }

        binding?.miscellaniousExerciseAddRemoveIB?.setOnClickListener {
            startExerciseListDialog(Constants.MISCELLANEOUS_LIST)
        }
        binding?.chestExerciseAddRemoveIB?.setOnClickListener {
            startExerciseListDialog(Constants.CHEST_LIST)
        }
        binding?.backExerciseAddRemoveIB?.setOnClickListener {
            startExerciseListDialog(Constants.BACK_LIST)
        }
        binding?.bicepExerciseAddRemoveIB?.setOnClickListener {
            startExerciseListDialog(Constants.BICEP_LIST)
        }
        binding?.tricepAndAbsExerciseAddRemoveIB?.setOnClickListener {
            startExerciseListDialog(Constants.TRICEP_AND_ABS_LIST)
        }
        binding?.shouldersExerciseAddRemoveIB?.setOnClickListener {
            startExerciseListDialog(Constants.SHOULDER_LIST)
        }
        binding?.legsExerciseAddRemoveIB?.setOnClickListener {
            startExerciseListDialog(Constants.LEGS_LIST)
        }


        val intent = Intent(this, ExerciseActivity::class.java)
        binding?.miccellenious?.setOnClickListener {
            intent.putExtra(Constants.EXERCISE_LIST_NAME, Constants.MISCELLANEOUS_LIST)
            startActivity(intent)
        }
        binding?.chestDay?.setOnClickListener {
            intent.putExtra(Constants.EXERCISE_LIST_NAME, Constants.CHEST_LIST)
            startActivity(intent)
        }
        binding?.backDay?.setOnClickListener {
            intent.putExtra(Constants.EXERCISE_LIST_NAME, Constants.BACK_LIST)
            startActivity(intent)
        }
        binding?.bicepDay?.setOnClickListener {
            intent.putExtra(Constants.EXERCISE_LIST_NAME, Constants.BICEP_LIST)
            startActivity(intent)
        }
        binding?.tricepsAndAbsDay?.setOnClickListener {
            intent.putExtra(Constants.EXERCISE_LIST_NAME, Constants.TRICEP_AND_ABS_LIST)
            startActivity(intent)
        }
        binding?.shoulderDay?.setOnClickListener {
            intent.putExtra(Constants.EXERCISE_LIST_NAME, Constants.SHOULDER_LIST)
            startActivity(intent)
        }
        binding?.legDay?.setOnClickListener {
            intent.putExtra(Constants.EXERCISE_LIST_NAME, Constants.LEGS_LIST)
            startActivity(intent)
        }
    }

    private fun startExerciseListDialog(exerciseListName: String) {
        val dialog = Dialog(this)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val dialogBinding = ExerciseSetModifierDialogBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        val allExerciseList = Constants.getAllExerciseList()
        var selectedExerciseList: ArrayList<Exercise>
        dialogBinding.searchView.clearFocus()
        lifecycleScope.launch {
            selectedExerciseList = getExerciseListBySetName(exerciseListName)
            setUpListInRecyclerView(allExerciseList, selectedExerciseList, exerciseListName, dialogBinding)
        }
        dialogBinding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                filterText(p0, allExerciseList)
                return true
            }

        })
        dialog.show()
    }

    private fun filterText(text: String?, allExerciseList: ArrayList<Exercise>) {
        val filterList = ArrayList<Exercise>()
        for (ex in allExerciseList) {
            if (text?.lowercase()?.let { ex.getName().lowercase().contains(it.lowercase()) } == true) {
                filterList.add(ex)
            }
        }

        if (filterList.isNotEmpty()) {
            setModifierAdapter?.setFilteredList(filterList)
        }
    }

    private fun setUpListInRecyclerView(
        allExerciseList: java.util.ArrayList<Exercise>,
        selectedExerciseList: java.util.ArrayList<Exercise>,
        exerciseListName: String,
        dialogBinding: ExerciseSetModifierDialogBinding
    ) {
        setModifierAdapter = ExerciseSetModifierAdapter(allExerciseList, selectedExerciseList, ArrayList()) {
            id, contains ->
            if ((contains && selectedExerciseList.size > 5) || (!contains && selectedExerciseList.size < 15)) {
                addOrRemoveExercise(id, exerciseListName, contains)
            } else {
                Toast.makeText(this, "Each set can only contain 5 to 15 exercises", Toast.LENGTH_SHORT).show()
            }
        }
        dialogBinding.selectedExercisesRV.adapter = setModifierAdapter
        dialogBinding.selectedExercisesRV.layoutManager = LinearLayoutManager(this)
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun addOrRemoveExercise(id: Int, listName: String, contains: Boolean) {
        when (listName) {
            Constants.MISCELLANEOUS_LIST -> {
                val miscellaneousExercisesDao = (application as HelloHealthyApp).miscellaneousExercisesDb.miscellaneousExercisesDao()
                lifecycleScope.launch {
                    if (contains) {
                        miscellaneousExercisesDao.delete(MiscellaneousExercisesEntity(id))
                    } else {
                        miscellaneousExercisesDao.insert(MiscellaneousExercisesEntity(id))
                    }
                }
            }
            Constants.CHEST_LIST -> {
                val chestExercisesDao = (application as HelloHealthyApp).chestExerciseDb.chestExerciseDao()
                lifecycleScope.launch {
                    if (contains) {
                        chestExercisesDao.delete(ChestExerciseEntity(id))
                    } else {
                        chestExercisesDao.insert(ChestExerciseEntity(id))
                    }
                }
            }
            Constants.BACK_LIST -> {
                val backExercisesDao = (application as HelloHealthyApp).backExerciseDb.backExerciseDao()
                lifecycleScope.launch {
                    if (contains) {
                        backExercisesDao.delete(BackExerciseEntity(id))
                    } else {
                        backExercisesDao.insert(BackExerciseEntity(id))
                    }
                }
            }
            Constants.BICEP_LIST -> {
                val bicepExercisesDao = (application as HelloHealthyApp).bicepExerciseDb.bicepExerciseDao()
                lifecycleScope.launch {
                    if (contains) {
                        bicepExercisesDao.delete(BicepExerciseEntity(id))
                    } else {
                        bicepExercisesDao.insert(BicepExerciseEntity(id))
                    }
                }
            }
            Constants.TRICEP_AND_ABS_LIST -> {
                val tricepAndAbsDao = (application as HelloHealthyApp).tricepAndAbsDb.tricepAndAbsDao()
                lifecycleScope.launch {
                    if (contains) {
                        tricepAndAbsDao.delete(TricepAndAbsEntity(id))
                    } else {
                        tricepAndAbsDao.insert(TricepAndAbsEntity(id))
                    }
                }
            }
            Constants.SHOULDER_LIST -> {
                val shoulderDao = (application as HelloHealthyApp).shoulderDb.shoulderDao()
                lifecycleScope.launch {
                    if (contains) {
                        shoulderDao.delete(ShoulderEntity(id))
                    } else {
                        shoulderDao.insert(ShoulderEntity(id))
                    }
                }
            }
            Constants.LEGS_LIST -> {
                val legsDao = (application as HelloHealthyApp).legsDb.legsDao()
                lifecycleScope.launch {
                    if (contains) {
                        legsDao.delete(LegsEntity(id))
                    } else {
                        legsDao.insert(LegsEntity(id))
                    }
                }
            }
        }
    }

    private suspend fun getExerciseListBySetName(exListName: String): ArrayList<Exercise> {
        val allExerciseList = Constants.getAllExerciseList()
        var ans = ArrayList<Exercise>()
        when (exListName) {
            Constants.MISCELLANEOUS_LIST -> {
                val miscellaneousExercisesDao = (application as HelloHealthyApp).miscellaneousExercisesDb.miscellaneousExercisesDao()
                val idList = ArrayList(miscellaneousExercisesDao.fetchAllMiscellaneousExercises())
                for (id in idList) {
                    ans.add(allExerciseList[id])
                }
            }
            Constants.CHEST_LIST -> {
                val chestExerciseDao = (application as HelloHealthyApp).chestExerciseDb.chestExerciseDao()
                val idList = ArrayList(chestExerciseDao.fetchAllChestExercises())
                for (id in idList) {
                    ans.add(allExerciseList[id])
                }
            }
            Constants.BACK_LIST -> {
                val backExerciseDao = (application as HelloHealthyApp).backExerciseDb.backExerciseDao()
                val idList = ArrayList(backExerciseDao.fetchAllBackExercises())
                for (id in idList) {
                    ans.add(allExerciseList[id])
                }
            }
            Constants.BICEP_LIST -> {
                val bicepExerciseDao = (application as HelloHealthyApp).bicepExerciseDb.bicepExerciseDao()
                val idList = ArrayList(bicepExerciseDao.fetchAllBicepExercises())
                for (id in idList) {
                    ans.add(allExerciseList[id])
                }
            }
            Constants.TRICEP_AND_ABS_LIST -> {
                val tricepAndAbsDao = (application as HelloHealthyApp).tricepAndAbsDb.tricepAndAbsDao()
                val idList = ArrayList(tricepAndAbsDao.fetchAllTricepAndAbsExercises())
                for (id in idList) {
                    ans.add(allExerciseList[id])
                }
            }
            Constants.SHOULDER_LIST -> {
                val shoulderDao = (application as HelloHealthyApp).shoulderDb.shoulderDao()
                val idList = ArrayList(shoulderDao.fetchAllShoulderExercises())
                for (id in idList) {
                    ans.add(allExerciseList[id])
                }
            }
            Constants.LEGS_LIST -> {
                val legsDao = (application as HelloHealthyApp).legsDb.legsDao()
                val idList = ArrayList(legsDao.fetchAllLegsExercises())
                for (id in idList) {
                    ans.add(allExerciseList[id])
                }
            }
            else -> {
                ans = allExerciseList
            }
        }
        for(exercise in ans) {
            exercise.setIsSelected(false)
            exercise.setIsCompleted(false)
        }
        return ans
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
