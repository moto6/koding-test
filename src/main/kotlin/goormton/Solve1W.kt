package goormton

fun main(args: Array<String>) {
    main0814(args)
}

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
    return op(i1,i2)
}
