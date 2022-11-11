package com.example.adi.hellohealthy

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adi.hellohealthy.databinding.ActivityExerciseBinding
import com.example.adi.hellohealthy.databinding.IcCustomDialogBackConfirmationBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var binding: ActivityExerciseBinding? = null

    private var countDownTimer: CountDownTimer? = null
    private var millisRemainingForRest: Long = 10000
    private var restTimerRunning: Boolean = false
    private var millisRemainingForExercise: Long = 30000
    private var exerciseTimerRunning: Boolean = false
    private var isRestingTime: Boolean = false

    private var exListName: String? = null
    private var exerciseList = ArrayList<Exercise>()
    private var currentExercisePosition = 0
    private var exercisesArrStr = ""

    private var tts: TextToSpeech? = null

    private var exerciseRVAdapter: ExerciseStatusAdapter? = null

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        tts = TextToSpeech(this, this)

        setSupportActionBar(binding?.exerciseActivityToolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.exerciseActivityToolBar?.setNavigationOnClickListener {
            customDialogForBackBtn()
        }


        if (Build.VERSION.SDK_INT >= 33) {
            onBackInvokedDispatcher.registerOnBackInvokedCallback(
                OnBackInvokedDispatcher.PRIORITY_DEFAULT
            ) {
                customDialogForBackBtn()
            }
        } else {
            onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    customDialogForBackBtn()
                }
            })
        }

        exListName = intent.getStringExtra(Constants.EXERCISE_LIST_NAME)
        supportActionBar?.title = exListName

        if (isRestingTime) {
            binding?.tVProgressRest?.text = "10"
        } else {
            binding?.tVProgressRest?.text = "30"
        }

        binding?.pauseOrPlayBtn?.setOnClickListener {
            if (isRestingTime && !restTimerRunning) {
                binding?.pauseOrPlayBtn?.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24)
                binding?.tVPauseOrPlay?.setText(R.string.pause)
                binding?.tVNote?.visibility = View.INVISIBLE
                restTimerRunning = true
                startCountDown(millisRemainingForRest)
            } else if (!isRestingTime && !exerciseTimerRunning) {
                binding?.pauseOrPlayBtn?.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24)
                binding?.tVPauseOrPlay?.setText(R.string.pause)
                binding?.tVNote?.visibility = View.INVISIBLE
                exerciseTimerRunning = true
                startCountDown(millisRemainingForExercise)
            } else {
                pauseCountDown()
            }
        }

        binding?.restartExerciseBtn?.setOnClickListener {
            restartExercise()
        }

        binding?.restartSetBtn?.setOnClickListener {
            restartSet()
        }

        binding?.homeBtn?.setOnClickListener {
            val intent = Intent(this@ExerciseActivity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        lifecycleScope.launch {
            exerciseList = getExerciseListBySetName(exListName!!)
            setUpUiAndExerciseStatusRV()
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpUiAndExerciseStatusRV() {
        var i = 0
        while (i < exerciseList.size) {
            exercisesArrStr = if (i == exerciseList.size - 1) {
                "$exercisesArrStr${exerciseList[i].getId()}"
            } else {
                "$exercisesArrStr${exerciseList[i].getId()},"
            }
            i++
        }
        exerciseRVAdapter = ExerciseStatusAdapter(exerciseList)
        binding?.rVExerciseStatus?.layoutManager =
            LinearLayoutManager(this@ExerciseActivity, LinearLayoutManager.HORIZONTAL, false)
        binding?.rVExerciseStatus?.adapter = exerciseRVAdapter
        exerciseRVAdapter?.notifyDataSetChanged()
        binding?.exerciseImgView?.setImageResource(
            exerciseList[currentExercisePosition].getImage()
        )
        binding?.tVTitle?.text = exerciseList[currentExercisePosition].getName()
        binding?.upcomingExerciseTextView?.visibility = View.GONE
    }

    private fun customDialogForBackBtn() {
        pauseCountDown()
        val dialog = Dialog(this)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val dialogBinding = IcCustomDialogBackConfirmationBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialog.setCanceledOnTouchOutside(false)
        dialogBinding.yesBtnBackConfirmationDialog.setOnClickListener {
            this@ExerciseActivity.finish()
            dialog.dismiss()
        }
        dialogBinding.noBtnBackConformationDialog.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    private fun restartSet() {
        pauseCountDown()
        binding?.llBtnHome?.visibility = View.GONE
        lifecycleScope.launch {
            exerciseList = getExerciseListBySetName(exListName!!)
            exerciseRVAdapter?.notifyDataSetChanged()
        }
        millisRemainingForRest = 10000
        millisRemainingForExercise = 30000
        isRestingTime = false
        exerciseTimerRunning = false
        restTimerRunning = false
        binding?.pauseOrPlayBtn?.setImageResource(R.drawable.ic_baseline_play_circle_outline_24)
        binding?.tVPauseOrPlay?.setText(R.string.play)
        binding?.tVNote?.visibility = View.VISIBLE
        binding?.lLBtn?.visibility = View.VISIBLE
        binding?.lLRestartExerciseBtn?.visibility = View.VISIBLE
        binding?.upcomingExerciseTextView?.visibility = View.GONE
        currentExercisePosition = 0
        binding?.exerciseImgView?.setImageResource(exerciseList[currentExercisePosition].getImage())
        binding?.tVTitle?.text = exerciseList[currentExercisePosition].getName()
        binding?.tVProgressRest?.text = "30"
        binding?.progressBarRest?.max = 30
        binding?.progressBarRest?.progress = 30
    }

    private fun restartExercise() {
        pauseCountDown()
        isRestingTime = false
        exerciseTimerRunning = true
        millisRemainingForExercise = 30000
        binding?.pauseOrPlayBtn?.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24)
        binding?.tVPauseOrPlay?.setText(R.string.pause)
        binding?.tVNote?.visibility = View.INVISIBLE
        startCountDown(millisRemainingForExercise)
    }

    private fun pauseCountDown() {
        if (countDownTimer != null) {
            countDownTimer?.cancel()
        }
        binding?.pauseOrPlayBtn?.setImageResource(R.drawable.ic_baseline_play_circle_outline_24)
        binding?.tVPauseOrPlay?.setText(R.string.play)
        binding?.tVNote?.visibility = View.VISIBLE
        restTimerRunning = false
        exerciseTimerRunning = false
        if (tts != null) {
            tts?.stop()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun startCountDown(timeRemaining: Long) {
        if (isRestingTime) {
            binding?.progressBarRest?.max = 10
        } else {
            binding?.progressBarRest?.max = 30
            exerciseList[currentExercisePosition].setIsSelected(true)
            exerciseRVAdapter?.notifyDataSetChanged()
        }
        binding?.progressBarRest?.progress = (timeRemaining / 1000).toInt()
        binding?.tVProgressRest?.text = (timeRemaining / 1000).toInt().toString()
        if (isRestingTime) {
            speakOut("${binding?.tVTitle?.text} ${binding?.upcomingExerciseTextView?.text}")
        } else {
            speakOut("${binding?.tVTitle?.text}")
        }

        countDownTimer = object: CountDownTimer(timeRemaining + 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if (isRestingTime) {
                    millisRemainingForRest = millisUntilFinished
                } else {
                    millisRemainingForExercise = millisUntilFinished
                }
                binding?.progressBarRest?.progress = (millisUntilFinished / 1000).toInt()
                binding?.tVProgressRest?.text = (millisUntilFinished / 1000).toInt().toString()
            }

            @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
            override fun onFinish() {
                millisRemainingForExercise = 30000
                millisRemainingForRest = 10000
                if (isRestingTime) {
                    binding?.upcomingExerciseTextView?.visibility = View.GONE
                    binding?.lLRestartExerciseBtn?.visibility = View.VISIBLE
                    isRestingTime = false
                    exerciseTimerRunning = true
                    binding?.exerciseImgView?.setImageResource(exerciseList[currentExercisePosition].getImage())
                    binding?.tVTitle?.text = exerciseList[currentExercisePosition].getName()
                    startCountDown(millisRemainingForExercise)
                } else {
                    exerciseList[currentExercisePosition].setIsCompleted(true)
                    exerciseRVAdapter?.notifyDataSetChanged()
                    currentExercisePosition++
                    if (currentExercisePosition >= exerciseList.size) {
                        binding?.lLBtn?.visibility = View.GONE
                        binding?.llBtnHome?.visibility = View.VISIBLE
                        binding?.tVNote?.visibility = View.GONE
                        binding?.exerciseImgView?.setImageResource(R.drawable.ic_well_done)
                        binding?.upcomingExerciseTextView?.visibility = View.GONE
                        binding?.tVTitle?.text = "Set Completed!!"
                        speakOut("Congratulations set completed")
                        addToDataBase()
                    } else {
                        binding?.lLRestartExerciseBtn?.visibility = View.GONE
                        isRestingTime = true
                        restTimerRunning = true
                        binding?.exerciseImgView?.setImageResource(R.drawable.ic_rest)
                        binding?.tVTitle?.setText(R.string.get_ready_for)
                        binding?.upcomingExerciseTextView?.visibility = View.VISIBLE
                        exerciseList[currentExercisePosition].setIsSelected(true)
                        exerciseRVAdapter?.notifyDataSetChanged()
                        binding?.upcomingExerciseTextView?.text = "Next Exercise is ${exerciseList[currentExercisePosition].getName()}"
                        startCountDown(millisRemainingForRest)
                    }
                }
            }
        }.start()
    }

    private fun addToDataBase() {
        val historyDao = (application as HelloHealthyApp).historyDb.historyDao()
        val c = Calendar.getInstance()
        val dateTime = c.time
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss aaa", Locale.getDefault())
        val date = sdf.format(dateTime)
        lifecycleScope.launch {
            historyDao.insert(HistoryEntity(date, exListName!!, exercisesArrStr))
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.ENGLISH)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The language specified is not supported")
            }
        } else {
            Log.e("TTS", "Initialization failed")
        }
    }

    private fun speakOut(text: String) {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
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
        if (countDownTimer != null) {
            countDownTimer?.cancel()
            exerciseTimerRunning = false
            restTimerRunning = false
            millisRemainingForExercise = 30000
            millisRemainingForRest = 10000
            isRestingTime = false
        }
        super.onDestroy()
        if (tts != null) {
            tts?.stop()
        }
        binding = null
    }
}