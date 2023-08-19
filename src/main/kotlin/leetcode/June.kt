package leetcode

import java.util.*
import kotlin.math.abs

fun main() {
//    print("4가 나와야 함 : ")
//    println(balancedStringSplit("RLRRLLRLRL")
    //print("9 나와야 한다 : ")
    //println(differenceOfSum(intArrayOf(1, 15, 6, 3)))
    println(cellsInRange("K1:L2"))
}


fun cellsInRange(s: String): List<String> {
    val answerList: MutableList<String> = mutableListOf()
    val (begin, end) = s.split(":")
    for (alphabetNumber in begin.toByteArray()[0].toInt()..end.toByteArray()[0].toInt()) {
        val alphabet:Char = alphabetNumber.toChar()
        for (rowNumber in begin.substring(1, 2).toInt()..end.substring(1, 2).toInt()) {
            answerList.add(alphabet.toString() + rowNumber.toString())
        }
    }
    return answerList
}


//


fun differenceOfSum(nums: IntArray): Int {
    val i1 = sumOfElements(nums)
    val i2 = sumOfDigits(nums)
    return abs(i1 - i2).toInt()
}

fun sumOfDigits(nums: IntArray): Int {
    val compList: MutableList<Int> = mutableListOf();
    for (i in nums.toList()) {
        decompositionAndAddToList(compList, i)
    }
    return compList.sum()
}

fun decompositionAndAddToList(compList: MutableList<Int>, i: Int) {
    when {
        i < 10 -> compList.add(i)
        else -> {
            compList.add(i % 10)
            decompositionAndAddToList(compList, i / 10)
        }
    }
}

fun sumOfElements(nums: IntArray): Int {
    return nums.sum()
}

//

fun balancedStringSplit(s: String): Int {
    val stack = Stack<Int>()
    var answer = 0;
    val split = s.toCharArray()
    for (elem in split) {
        stack.push(converterRL(elem))
        if (isBalanced(stack)) {
            answer++;
            stack.clear()
        }
    }
    return answer;
}

fun isBalanced(stack: Stack<Int>): Boolean {
    return stack.toList().sum() == 0
}

fun converterRL(s: Char): Int {
    return when {
        s.equals('R') -> -1
        s.equals('L') -> 1
        else -> throw RuntimeException()
    }
}


fun numberOfSteps(num: Int): Int {
    return when {
        num == 0 -> return 0
        num % 2 == 0 -> stepEven(num / 2, 1)
        else -> stepOdd(num - 1, 1)
    }
}

fun stepOdd(num: Int, depth: Int): Int {
    return when {
        num == 0 -> depth;
        num % 2 == 0 -> stepEven(num / 2, depth + 1)
        else -> stepOdd(num - 1, depth + 1)
    }
}

fun stepEven(num: Int, depth: Int): Int {
    return when {
        num == 0 -> depth
        num % 2 == 0 -> stepEven(num / 2, depth + 1)
        else -> stepOdd(num - 1, depth + 1)
    }
}


class OrderedStream(n: Int) {
    val list = Array<String>(n + 1) { "" } // 초기 크기와 기본값 지정
    fun insert(idKey: Int, value: String): List<String> {
        list[idKey] = value;
        return list.toList()
    }
}
