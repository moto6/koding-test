package leetcode

import java.util.*

fun main() {
    print("4가 나와야 함 : ")
    println(balancedStringSplit("RLRRLLRLRL"))

}

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

fun isBalanced(stack: Stack<Int>) : Boolean {
   return  stack.toList().sum() == 0
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
        num%2 == 0 -> stepEven(num/2,1)
        else -> stepOdd(num-1,1)
    }
}

fun stepOdd(num: Int, depth: Int):Int {
    return when {
        num ==0 -> depth;
        num%2 == 0 -> stepEven(num/2,depth+1)
        else -> stepOdd(num-1,depth+1)
    }
}

fun stepEven(num: Int, depth: Int) :Int{
    return when {
        num ==0 -> depth
        num%2 == 0 -> stepEven(num/2,depth+1)
        else -> stepOdd(num-1,depth+1)
    }
}
