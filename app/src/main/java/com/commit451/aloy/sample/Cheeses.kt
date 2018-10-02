package com.commit451.aloy.sample

import java.util.*

/**
 * Get some cheese
 */
object Cheeses {

    private val RANDOM = Random()

    val randomCheeseDrawable: Int
        get() {
            when (RANDOM.nextInt(5)) {
                1 -> return R.drawable.cheese_2
                2 -> return R.drawable.cheese_3
                3 -> return R.drawable.cheese_4
                4 -> return R.drawable.cheese_5
                else -> return R.drawable.cheese_1
            }
        }

    val CHEESE_STRINGS = arrayOf(
            "Abbaye de Belloc",
            "Canadian Cheddar",
            "Lajta",
            "Meredith Blue",
            "Ossau-Iraty"
    )

    val randomCheeseName: String
        get() = CHEESE_STRINGS[RANDOM.nextInt(CHEESE_STRINGS.size - 1)]

    val randomCheese: Cheese
        get() = Cheese(randomCheeseDrawable, randomCheeseName)

}