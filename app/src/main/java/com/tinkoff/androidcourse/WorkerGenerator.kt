package com.tinkoff.androidcourse

import java.util.*

class WorkerGenerator {
    private val randomGenerator = Random(47)
    private val maleNames = arrayListOf("John", "Bill", "Bob", "Oliver", "Jack", "Harry", "George", "William", "Henry")
    private val femaleNames = arrayListOf("Anna", "Emma", "Sophie", "Jessica", "Scarlett", "Molly", "Lucy", "Megan")
    private val surnames = arrayListOf("Green", "Smith", "Taylor", "Brown", "Wilson", "Walker", "White", "Jackson", "Wood")
    private val femalePhoto = arrayListOf(R.drawable.ic_female_black, R.drawable.ic_female_green, R.drawable.ic_female_red, R.drawable.ic_female_magnetta)
    private val malePhoto = arrayListOf(R.drawable.ic_male_black, R.drawable.ic_male_green, R.drawable.ic_male_red, R.drawable.ic_male_magnetta)
    private val positions = arrayListOf("Android programmer", "iOs programmer", "Web programmer", "Designer")

    private fun randomIndex(maxValue: Int) = randomGenerator.nextInt(maxValue)

    fun generateWorker() = Worker().apply {
        if (randomIndex(2) == 0) {
            gender = Gender.MALE
            val randomName = maleNames[randomIndex(maleNames.size)]
            val randomSurname = surnames[randomIndex(surnames.size)]
            name = "$randomName $randomSurname"
            photo = malePhoto[randomIndex(malePhoto.size)]
        } else {
            gender = Gender.FEMALE
            val randomName = femaleNames[randomIndex(femaleNames.size)]
            val randomSurname = surnames[randomIndex(surnames.size)]
            name = "$randomName $randomSurname"
            photo = femalePhoto[randomIndex(femalePhoto.size)]
        }
        age = Integer.toString(20 + randomGenerator.nextInt(10))
        position = positions[randomIndex(positions.size)]
    }

    fun generateWorkers(workersCount: Int) = ArrayList<Worker>().apply {
        for (i in 0 until workersCount) {
            add(generateWorker())
        }
    }
}
