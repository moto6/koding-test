package groom

import kotlin.math.abs


fun main(args: Array<String>) {
    val (saltyWater7percentOfGram, water) = readLine()!!
        .split(" ")
        .map { it -> it.toDouble() }
    val saltWeight = saltWeight(saltyWater7percentOfGram, 7.toDouble())
    println(
        String.format(
            "%.2f",
            density(saltWeight, water + saltyWater7percentOfGram)
        )
    )
}

fun density(saltWeight: Double, waterWeight: Double): Double {
    return saltWeight * 100 / waterWeight
}

fun saltWeight(saltyWater: Double, density: Double): Double {
    return (density * saltyWater) / (100).toDouble()
}


fun mainRobotCleaner(args: Array<String>) {
    val count = readLine()!!.toInt()

    for (i in 1..count) {
        val (x, y, n) = readLine()!!.split(" ").map { it -> it.toInt() }
        when (canArrive(x, y, n)) {
            true -> println("YES")
            else -> println("NO")
        }

    }

    """
    
4
-5 -2 7
5 -5 2
0 5 6
1 2 7

""".trimIndent()

}

fun canArrive(x: Int, y: Int, n: Int): Boolean {
    val sum = abs(x) + abs(y);
    return (sum <= n && (sum - n) % 2 == 0)
}
