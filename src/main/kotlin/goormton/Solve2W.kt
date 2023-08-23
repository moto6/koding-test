package goormton

fun main(args: Array<String>) {
    //fun main0822(args: Array<String>) {
    val (sizeOfMap, flag) = readLine()!!.split(" ").map { it.toInt() }
    val mineMap: MutableList<MutableList<Char>> = mutableListOf()

    for (i in 0 until sizeOfMap) {
        val charList = readLine()!!
            .split(" ")
            .map { it.first() }
            .map { if (it == '1') 'M' else '-' }
            .toMutableList()
        mineMap.add(charList)
    }

    for (rowIndex in 0 until sizeOfMap) {
        val row = mineMap[rowIndex]
        for (colIndex in 0 until sizeOfMap) {
            val col = row.get(colIndex)
            if (col == '-') {
                mineMap[rowIndex][colIndex] =
                    adjacentMineCount(mineMap, mineMap.indexOf(row), row.indexOf(col));
            }
        }
    }

    var answer = 0
    for (rowIndex in 0 until sizeOfMap) {
        for (colIndex in 0 until sizeOfMap) {
            answer += when {
                //return count.digitToChar() //구름에서 사용중인 코틀린 버전이 낮아서 사용이 안됨 ㅠㅠ 고쳐주세요
                mineMap[rowIndex][colIndex] == (flag + 48).toChar() -> 1
                else -> 0
            }
        }
    }
    print(answer)
    //print(mineMap[flag][flag])
}

fun adjacentMineCount(
    mineMap: MutableList<MutableList<Char>>,
    indexOfRow: Int,
    indexOfCol: Int
): Char {
    var count = 0;
    for (row in indexOfRow - 1..indexOfRow + 1) {
        for (col in indexOfCol - 1..indexOfCol + 1) {
            count += when {
                isMine(row, col, mineMap) -> 1
                else -> 0
            }
        }
    }
    //return count.digitToChar() //구름에서 사용중인 코틀린 버전이 낮아서 사용이 안된다
    return (count + 48).toChar();
}

fun isMine(row: Int, col: Int, mineMap: MutableList<MutableList<Char>>): Boolean {
    if (!(0 <= row && row < mineMap.size)) {
        return false
    }
    if (!(0 <= col && col < mineMap.size)) {
        return false
    }
    return mineMap[row][col] == 'M'
}

/*
4 2
0 0 0 1
0 0 1 0
0 0 1 0
0 1 1 1
=2

5 8
0 1 1 1 1
0 1 0 1 0
0 1 1 1 1
0 1 1 0 1
0 1 0 1 0
=1
 */

fun main0821(args: Array<String>) {
    readLine()!!.toInt()
    val inputString = readLine()!!
    val pointMapper: Map<String, Int> = getPointMapper(inputString);

    var maxi = -1;

    val stringSize = inputString.length
    for (first in 1 until stringSize) {
        for (second in first + 1 until stringSize) {
            val section1: String = inputString.substring(0, first)
            val section2: String = inputString.substring(first, second)
            val section3: String = inputString.substring(second, stringSize)

            maxi = Math.max(
                pointMapper[section1]!! + pointMapper[section2]!! + pointMapper[section3]!!,
                maxi
            )
        }
    }
    print(maxi + 3)
}

fun getPointMapper(inputString: String): Map<String, Int> {
    val subStrings: MutableSet<String> = mutableSetOf()
    //getSubStringRecursive(0, inputString, subStrings)
    getSubStringV2(0, inputString, subStrings)
    //print("hi");
    val stringList: List<String> = subStrings
        .sorted()
    //print(stringList)
    return (stringList.indices).associateBy { stringList[it] }
}

fun getSubStringRecursive(beginIndex: Int, inputString: String, subStrings: MutableSet<String>) {
    if (beginIndex > inputString.length) {
        return
    }
    for (step in 1 until inputString.length - 1) {
        if ((beginIndex + step) > inputString.length) {
            return
        }
        subStrings.add(inputString.substring(beginIndex, beginIndex + step))
        getSubStringRecursive(beginIndex + step, inputString, subStrings)
    }
}


fun getSubStringV2(beginIndex22: Int, inputString: String, subStrings: MutableSet<String>) {
    for (length in 1 until inputString.length - 1) {
        for (startPoint in inputString.indices) {
            if ((startPoint + length) > inputString.length) {
                continue
            }
            subStrings.add(inputString.substring(startPoint, startPoint + length))
        }
    }
}

val note0821: String = """
    일단 부르트포스로 가능한지 생각해봤다. 부르트포스가 가능하다면 그냥 문제에서 주어진대로 구현하면 끝날수 있기 때문
    양극단(맨앞 & 맨뒤) 지점을 제외한 두곳에 콤마를 배치하면 3개의 구간으로 문자열을 나눌수 있다.
    즉 n * (n-1) 가지 경우에 대해서 탐색을 허용한다면 브루트포스이고 이 문제의 최대 길이가 300 이기 때문에 최악의 경우 9만건정도만 계산한다면 답을 구할수 있다
    나쁘지 않은 연산횟수라 브루트 포스로 문제를 풀어보겠습니다.
    
    
    부분문자열을 구하기 위해서는 재귀함수를 이용합니다.
    JVM 언어는 heap 공간에 데이터타입을 저장하기 때문에 c++ 에서의 전역변수 효과를 낼 수 있습니다.
    a-bcb ,ab-cd, abc-d
    b-
    
    
    문제가 출제되고 
    
    5/1 은 JVM 억까가 심해 라고 하면서 다른언어로 구현하는 뻘짓을 했고
    
    48시간 중에서 40분 남겨놓고 풀었다.. 후..
    
        """

val ssss = """
100
abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuv


26
abcdefghijklmnopqrstuvwxyz
"""
