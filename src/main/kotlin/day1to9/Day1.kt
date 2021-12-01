package day1to9

import utility.ExecutionTime
import utility.Utility

fun main() {
    ExecutionTime.start()
    val expenses = Utility.readIntegerFile("AoC2020.Day1to9\\puzzle1")
//        .sorted()
    expenses.sort()

    fun findTwoFactors(targetValue : Int) : IntArray {
        var smallIndex = 0
        var bigIndex = expenses.size -1
        for (i in expenses.indices) {
            val sum = expenses[smallIndex] + expenses[bigIndex]
            when {
                sum > targetValue -> bigIndex--
                sum < targetValue -> smallIndex++
                else -> return intArrayOf(expenses[smallIndex], expenses[bigIndex])
            }
        }
        throw NoSuchElementException()
    }

    fun findThreeFactors(targetValue: Int): IntArray {
        for (currentInt in expenses) {
            try {
                val twoFactors = findTwoFactors(targetValue - currentInt)
                if (twoFactors[0] != currentInt && twoFactors[1] != currentInt) {
                    return intArrayOf(currentInt, twoFactors[0], twoFactors[1])
                }
            } catch (e: NoSuchElementException) {
            }
        }
        throw NoSuchElementException()
    }

    val (twoFirst, twoSecond) = findTwoFactors(2020)
    println("Found $twoFirst and $twoSecond. Final product is " + twoFirst*twoSecond)
    val (threeFirst, threeSecond, threeThird) = findThreeFactors(2020)
    println("Found $threeFirst, $threeSecond and $threeThird. Final product is " + threeFirst*threeSecond*threeThird)

    ExecutionTime.stop()
}