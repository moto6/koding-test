package goormton

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    //main0814(args)
    main0818(args)
}

fun main0818(args: Array<String>) {

    val (count, indexOfAnswer) = readLine()!!.split(" ").map { s -> s.toInt() }
    val scanner = Scanner(System.`in`)

    val unOrderedList: List<Int> = (1..count)
        .asSequence()
        .map { scanner.next().toInt() }
        .toList()

    val orderedList = unOrderedList
        .sortedWith(
            compareByDescending<Int> { countOf1AtBinary(it) }
                .thenByDescending { it }
        )
        .toList()
    print(orderedList[indexOfAnswer - 1])


    //helper(unOrderedList,orderedList)

}

fun helper( unOrderedList:List<Int>, orderedList:List<Int>) {
    println("\n============\n")
    println(unOrderedList)
    val elemAndCount =
        unOrderedList.associate { i -> Pair(i, countOf1AtBinary(i)) }
    val elemAndBinaryString =
        unOrderedList.associate { i -> Pair(i, Integer.toBinaryString(i)!!) }
    println(elemAndCount)
    println(elemAndBinaryString)

    println(orderedList)

}

fun countOf1AtBinary(decimalNumber: Int): Int {
    return Integer.toBinaryString(decimalNumber)
        .toCharArray()
        .filter { it -> it == '1' }
        .size
}

// ----

fun main0814(args: Array<String>) {
    val (w, r) = readLine()!!
        .split(" ")
        .map { it.toDouble() }
    print((w * ((1.0000 + (r / 30.0000)))).toInt())
}


//-------------------

fun main0815(args: Array<String>) {
    val count = readLine()!!
        .toInt()
    val (hour, min) = readLine()!!
        .split(" ")
        .map { it.toInt() }
    val numberList = mutableListOf<Int>()
    for (i in 0 until count) {
        numberList.add(readLine()!!.toInt())
    }
    val totalMinute = numberList.sum()
    val answerHour = calHour(hour, totalMinute, min).toString()
    val answerMin = calMin(totalMinute, min).toString()
    print("$answerHour $answerMin")
}

fun calHour(hour: Int, totalMinute: Int, min: Int): Int {
    return (hour + carryMiMin(totalMinute, min)) % 24
}

fun calMin(totalMinute: Int, min: Int): Int {
    return (totalMinute + min) % 60
}

fun carryMiMin(totalMinute: Int, min: Int): Int {
    return (totalMinute + min) / 60
}


//-------------------


//3일차


fun main0816(args: Array<String>) {
    var sum = 0;
    for (i in 0 until readLine()!!.toInt()) {
        sum += calculateExpression(readLine()!!)
    }
    print(sum)
}

fun calculateExpression(expression: String): Int {
    val split = expression.split(" ")
    return operate(split[0].toInt(), split[2].toInt(), when (split[1]) {
        "*" -> { x, y -> x * y }
        "/" -> { x, y -> x / y }
        "+" -> { x, y -> x + y }
        "-" -> { x, y -> x - y }
        else -> throw IllegalArgumentException("Invalid operator: ${split[1]}")
    })
}

fun operate(i1: Int, i2: Int, op: (Int, Int) -> Int): Int {
    return op(i1, i2)
}


fun main0817(args: Array<String>) {
    readLine()
    val comps: List<Int> = readLine()!!
        .split(" ")
        .map { s -> s.toInt() }.toList()
    val max: Int = comps.maxOf { it.toInt() }
    when (isIncreasing(0, comps.indexOf(max), comps) &&
            isDecreasing(comps.indexOf(max), comps.size, comps)
    ) {
        true -> print(comps.sum())
        else -> {
            print("0")
        }
    }
}

fun isDecreasing(beginIndex: Int, endIndex: Int, collection: List<Int>): Boolean {
    var pointValue: Int = collection[beginIndex]
    for (i in beginIndex until endIndex) {
        if (pointValue >= collection[i]) {
            pointValue = collection[i]
        } else {
            return false
        }
    }
    return true
}

fun isIncreasing(beginIndex: Int, endIndex: Int, collection: List<Int>): Boolean {
    var pointValue: Int = collection[beginIndex]
    for (i in beginIndex until endIndex) {
        if (pointValue <= collection[i]) {
            pointValue = collection[i]
        } else {
            return false
        }
    }
    return true
}

