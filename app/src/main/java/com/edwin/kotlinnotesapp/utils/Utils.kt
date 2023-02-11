package com.edwin.kotlinnotesapp.utils

import com.edwin.kotlinnotesapp.R
import kotlin.random.Random

class Utils {

    companion object {

        fun getRandomColor(): Int {

            val list = ArrayList<Int>()
            list.add(R.color.noteColor1)
            list.add(R.color.noteColor2)
            list.add(R.color.noteColor3)
            list.add(R.color.noteColor4)
            list.add(R.color.noteColor5)
            list.add(R.color.noteColor6)
            list.add(R.color.noteColor7)
            list.add(R.color.noteColor8)
            list.add(R.color.noteColor9)
            list.add(R.color.noteColor10)

            val seed = System.currentTimeMillis().toInt()
            val randomIndex = Random(seed).nextInt(list.size)

            return list[randomIndex]
        }

    }
}