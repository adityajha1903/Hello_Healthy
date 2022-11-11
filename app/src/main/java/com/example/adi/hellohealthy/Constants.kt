package com.example.adi.hellohealthy

object Constants {


    /**
     Constant strings for calling different exercise lists
     **/
    const val EXERCISE_LIST_NAME = "exercise_list"
    const val MISCELLANEOUS_LIST = "Miscellaneous"
    const val CHEST_LIST = "Chest"
    const val BACK_LIST = "Back"
    const val BICEP_LIST = "Bicep and Arm"
    const val TRICEP_AND_ABS_LIST = "Tricep and Abs"
    const val SHOULDER_LIST = "Shoulder"
    const val LEGS_LIST = "Legs"



    /**
    All the exercises available in the app
     **/
    private val lunges = Exercise(0, "Lunges", R.drawable.ic_lunges, isCompleted = false, isSelected = false)
    private val pushUps = Exercise(1, "Push-ups", R.drawable.ic_push_ups, isCompleted = false, isSelected = false)
    private val bodyWeightSquats = Exercise(2, "Body weight squats", R.drawable.ic_squats, isCompleted = false, isSelected = false)
    private val overHeadDumbbellPress = Exercise(3, "Overhead dumbbell press", R.drawable.ic_overhead_dumbbelll_press, isCompleted = false, isSelected = false)
    private val dumbbellRows = Exercise(4, "Dumbbell rows", R.drawable.ic_dumbbell_rows, isCompleted = false, isSelected = false)
    private val romanianDeadLift = Exercise(5, "Romanian dead-lift", R.drawable.ic_romanian_deadlift, isCompleted = false, isSelected = false)
    private val burpees = Exercise(6, "Burpees", R.drawable.ic_burpees, isCompleted = false, isSelected = false)
    private val sidePlanks = Exercise(7, "Side planks", R.drawable.ic_side_planks, isCompleted = false, isSelected = false)
    private val planks = Exercise(8, "Planks", R.drawable.ic_planks, isCompleted = false, isSelected = false)
    private val gluteBridge = Exercise(9, "Glute bridge", R.drawable.ic_glute_bridge, isCompleted = false, isSelected = false)
    private val halfPushUpHover = Exercise(10, "Half push up hover", R.drawable.ic_half_push_up_hover, false, isSelected = false)
    private val jumpingJack = Exercise(11, "Jumping jack", R.drawable.ic_jumping_jack, isCompleted = false, isSelected = false)
    private val forearmPlank = Exercise(12,"Forearm Plank", R.drawable.ic_forearm_plank, isCompleted = false, isSelected = false)
    private val pullUp = Exercise(13, "Pull up", R.drawable.ic_pull_up, isCompleted = false, isSelected = false)
    private val hollowBodyHold = Exercise(14, "Hollow Body Hold", R.drawable.ic_hollow_body_hold, isCompleted = false, isSelected = false)
    private val stepUps = Exercise(15, "Step ups", R.drawable.ic_step_ups, isCompleted = false, isSelected = false)
    private val tricepDips = Exercise(16, "Tricep dips", R.drawable.ic_dips, isCompleted = false, isSelected = false)
    private val squatsJump = Exercise(17, "Squat jumps", R.drawable.ic_squats_jumps, isCompleted = false, isSelected = false)
    private val sideLegRaises = Exercise(18, "Side leg raises", R.drawable.ic_side_leg_raises, isCompleted = false, isSelected = false)
    private val warriorBalance = Exercise(19, "Warrior balance", R.drawable.ic_warrior_balance, isCompleted = false, isSelected = false)
    private val goodMorning = Exercise(20, "Good morning", R.drawable.ic_good_morning, isCompleted = false, isSelected = false)
    private val hipThrust = Exercise(21, "Hip thrust", R.drawable.ic_hip_thrust, isCompleted = false, isSelected = false)
    private val skaters = Exercise(22, "Skaters", R.drawable.ic_skaters, isCompleted = false, isSelected = false)
    private val calfRaises = Exercise(23, "Calf raises", R.drawable.ic_calf_raises, isCompleted = false, isSelected = false)
    private val dumbBellPullover = Exercise(24, "Dumbbell pullover", R.drawable.ic_dumbell_pullover, isCompleted = false, isSelected = false)
    private val chestDips = Exercise(25, "Chest Dips", R.drawable.ic_dips_chest, isCompleted = false, isSelected = false)
    private val chestPress = Exercise(26, "Chest press", R.drawable.ic_chest_press, isCompleted = false, isSelected = false)
    private val twoArmDumbbellRow = Exercise(27, "Two-arm dumbbell row", R.drawable.ic_two_arm_dumbbell_row, isCompleted = false, isSelected = false)
    private val deltoidRaises = Exercise(28, "Deltoid raises", R.drawable.ic_deltoid_raises, isCompleted = false, isSelected = false)
    private val dumbbellChestFly = Exercise(29, "Dumbbell chest fly", R.drawable.ic_dumbbell_chest_fly, isCompleted = false, isSelected = false)
    private val inclinedPushUps = Exercise(30, "Inclined push-ups", R.drawable.ic_inclined_pushups, isCompleted = false, isSelected = false)
    private val t_raises = Exercise(31, "T raises", R.drawable.ic_t_raises, isCompleted = false, isSelected = false)
    private val singleArmDumbbellRows = Exercise(32, "Single-arm dumbbell rows", R.drawable.ic_single_arm_dumbbell_rows, isCompleted = false, isSelected = false)
    private val deltRaises = Exercise(33, "Delt raise", R.drawable.ic_delt_raises, isCompleted = false, isSelected = false)
    private val plankWithLateralArmRaises = Exercise(34, "Plank with lateral arm raise", R.drawable.ic_plank_with_lateral_arm_raises, isCompleted = false, isSelected = false)
    private val pushUpHold = Exercise(35, "Push-up hold", R.drawable.ic_push_up_hold, isCompleted = false, isSelected = false)
    private val backAndBootyBlasters = Exercise(36, "Back and booty blasters", R.drawable.ic_back_and_booty_blasters, isCompleted = false, isSelected = false)
    private val twister = Exercise(37, "Twister", R.drawable.ic_twister, isCompleted = false, isSelected = false)
    private val pilatesPress = Exercise(38, "Pilates press", R.drawable.ic_pilates_press, isCompleted = false, isSelected = false)
    private val invertedRows = Exercise(39, "Inverted rows", R.drawable.ic_inverted_rows, isCompleted = false, isSelected = false)
    private val bandPullApart = Exercise(40, "Band pull apart", R.drawable.ic_band_pull_apart, isCompleted = false, isSelected = false)
    private val chinUp = Exercise(41, "Chin up", R.drawable.ic_chin_up, isCompleted = false, isSelected = false)
    private val wideLiftedBicepCurl = Exercise(42, "Wide lifted biceps curl", R.drawable.ic_wide_lifted_bicep_curl, isSelected = false, isCompleted = false)
    private val bicepsCurl = Exercise(43, "Biceps curl", R.drawable.ic_bicep_curl, isCompleted = false, isSelected = false)
    private val plankShoulderTap = Exercise(44, "Plank shoulder tap", R.drawable.ic_plank_shoulder_tap, isCompleted = false, isSelected = false)
    private val seatedRowWithResistanceBand = Exercise(45, "Seated row with resistance band", R.drawable.ic_seated_row_with_resistance_band, isCompleted = false, isSelected = false)
    private val standingBicepStretch = Exercise(46, "Standing bicep stretch", R.drawable.ic_standing_bicep_stretch, isCompleted = false, isSelected = false)
    private val seatedBicepStretch = Exercise(47, "Seated bicep stretch", R.drawable.ic_seated_bicep_stretch, isCompleted = false, isSelected = false)
    private val inchworm = Exercise(48, "Inchworm", R.drawable.ic_inchworm, isCompleted = false, isSelected = false)
    private val zottmanCurl = Exercise(49, "Zottman curl", R.drawable.ic_zottman_curl, isCompleted = false, isSelected = false)
    private val sidePlankWithArmExtension = Exercise(50, "Side plank with arm extension", R.drawable.ic_side_plank_with_arm_extension, isCompleted = false, isSelected = false)
    private val dumbbellLyingTricepExtension = Exercise(51, "Dumbbell lying tricep extension", R.drawable.ic_dumbbell_lying_tricep_extension, isCompleted = false, isSelected = false)
    private val crunch = Exercise(52, "Crunch", R.drawable.ic_crunch, isCompleted = false, isSelected = false)
    private val overheadTricepsExtensions = Exercise(53, "Overhead Triceps Extensions", R.drawable.ic_overhead_triceps_extensions, isCompleted = false, isSelected = false)
    private val bicycleCrunches = Exercise(54, "Bicycle crunches", R.drawable.ic_bicycle_crunches, isCompleted = false, isSelected = false)
    private val ropePushDowns = Exercise(55, "Rope push downs", R.drawable.ic_rope_push_downs, isCompleted = false, isSelected = false)
    private val supineDeadBug = Exercise(56, "Supine dead bug", R.drawable.ic_supine_dead_bug, isCompleted = false, isSelected = false)
    private val lyingTricepsExtensions = Exercise(57, "Lying triceps extensions", R.drawable.ic_lying_triceps_extensions, isCompleted = false, isSelected = false)
    private val hangingKneeRaise = Exercise(58, "Hanging knee raise", R.drawable.ic_hanging_knee_raise, isCompleted = false, isSelected = false)
    private val dumbbellLateralRaise = Exercise(59, "Dumbbell lateral raise", R.drawable.ic_dumbbell_lateral_raise, isCompleted = false, isSelected = false)
    private val militaryPress = Exercise(60, "Military press", R.drawable.ic_military_press, isCompleted = false, isSelected = false)
    private val reverseFly = Exercise(61, "Reverse fly", R.drawable.ic_reverse_fly, isCompleted = false, isSelected = false)
    private val arnoldPress = Exercise(62, "Arnold press", R.drawable.ic_arnold_press, isCompleted = false, isSelected = false)
    private val frontDeltoidRaise = Exercise(63, "Front deltoid raise", R.drawable.ic_front_deltoid_raise, isCompleted = false, isSelected = false)
    private val divingDolphin = Exercise(64, "Diving dolphin", R.drawable.ic_diving_dolphin, isCompleted = false, isSelected = false)
    private val cleanSquatPress = Exercise(65, "Clean squat press", R.drawable.ic_clean_squat_press, isCompleted = false, isSelected = false)
    private val scarecrowPress = Exercise(66, "Scarecrow press", R.drawable.ic_scarecrow_press, isCompleted = false, isSelected = false)



    /**
    An Arraylist of all the exercises available in the app

    name = ALL_EXERCISE_LIST
     **/
    private val ALL_EXERCISE_LIST = arrayListOf(lunges, pushUps, bodyWeightSquats, overHeadDumbbellPress, dumbbellRows, romanianDeadLift, burpees, sidePlanks, planks, gluteBridge, halfPushUpHover, jumpingJack, forearmPlank, pullUp, hollowBodyHold, stepUps, tricepDips, squatsJump, sideLegRaises, warriorBalance, goodMorning, hipThrust, skaters, calfRaises, dumbBellPullover, chestDips, chestPress, twoArmDumbbellRow, deltoidRaises, dumbbellChestFly, inclinedPushUps, t_raises, singleArmDumbbellRows, deltRaises, plankWithLateralArmRaises, pushUpHold, backAndBootyBlasters, twister, pilatesPress, invertedRows, bandPullApart, chinUp, wideLiftedBicepCurl, bicepsCurl, plankShoulderTap, seatedRowWithResistanceBand, standingBicepStretch, seatedBicepStretch, inchworm, zottmanCurl, sidePlankWithArmExtension, dumbbellLyingTricepExtension, crunch, overheadTricepsExtensions, bicycleCrunches, ropePushDowns, supineDeadBug, lyingTricepsExtensions, hangingKneeRaise, dumbbellLateralRaise, militaryPress, reverseFly, arnoldPress, frontDeltoidRaise, divingDolphin, cleanSquatPress, scarecrowPress)



    /**
    A function to get the Arraylist of all the exercises available in the app

    name = getAllExerciseList()

    return type = ArrayList<Exercise>
     **/
    fun getAllExerciseList(): ArrayList<Exercise> {
        return ALL_EXERCISE_LIST
    }



    /**
    A function to get different exercises Arraylist using a string for example ("1,2,4,5,7,9"), here the numbers in the string indicates the ids of exercise needed to be passed in the Arraylist

    name = getExerciseListForDisplayingOnHistoryDialog(exerciseArrStr: String)

    return type = ArrayList<Exercise>
     **/
    fun getExerciseListForDisplayingOnHistoryDialog(exerciseArrStr: String): ArrayList<Exercise> {
        val ans = ArrayList<Exercise>()
        val arrExerciseIdsStr = exerciseArrStr.split(",")
        for (i in arrExerciseIdsStr) {
            ans.add(ALL_EXERCISE_LIST[i.toInt()])
        }
        return ans
    }
}