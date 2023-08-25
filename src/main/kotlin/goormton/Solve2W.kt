package goormton

import java.util.*

class Cmd(
    val count: Int,
    val direction: Char
) {
    fun execute(curent: Pair<Int, Int>): Pair<Int, Int> {
        // Pair(row, col)
        if (this.direction == 'U') {
            return Pair(curent.first - count, curent.second)
        }

        if (this.direction == 'D') {
            return Pair(curent.first + count, curent.second)
        }

        if (this.direction == 'R') {
            return Pair(curent.first, curent.second + count)
        }

        if (this.direction == 'L') {
            return Pair(curent.first, curent.second - count)
        }
        throw RuntimeException("파싱이 잘못된듯?")
    }
    override fun toString(): String {
        return String.format("$direction $count")
    }
}

fun readCmd(n: Int): MutableList<List<Cmd>> {
    val cmds: MutableList<List<Cmd>> = mutableListOf()
    for (i in 1..n) {
        val map = readLine()!!
            .split(" ")
            .map { it -> Cmd(it.first().toInt()-'0'.toInt(), it.last()) }
        cmds.add(map)
    }
    return cmds;
}

fun readPosition(): Pair<Int, Int> {
    val (x, y) = readLine()!!
        .split(" ")
        .map { it.toInt() }
    // Pair(row, col) 열 세로 콜
    return Pair(x - 1, y - 1)
}

fun initMap(n: Int): MutableList<MutableList<Boolean>> {
    val initalMap: MutableList<MutableList<Boolean>> = mutableListOf()
    for (i in 1..n) {
        initalMap.add(Collections.nCopies(n, false).toMutableList())
    }
    return initalMap
    //return Collections.nCopies(n, Collections.nCopies(n, false).toMutableList()).toMutableList()
}

fun main(args: Array<String>) {
    val N = readLine()!!.toInt()
    val groomPosition = readPosition()
    val groomVisited: MutableList<MutableList<Boolean>> = initMap(N)
    val playerPosition = readPosition()
    val playerVisited: MutableList<MutableList<Boolean>> = initMap(N)
    val cmd2D: MutableList<List<Cmd>> = readCmd(N)
    val groomScore: Int = getScore(N, groomPosition, groomVisited, cmd2D)
    val playerScore: Int = getScore(N, playerPosition, playerVisited, cmd2D)
    when {
        groomScore > playerScore -> print("goorm $groomScore")
        groomScore < playerScore -> print("player $playerScore")
        else -> throw RuntimeException("???")
    }
}

/*
3
1 1
3 3
1L 2L 1D
2U 3R 1D
2R 2R 1U
 */

fun getScore(
    n: Int,
    curPosition: Pair<Int, Int>,
    isVisited: MutableList<MutableList<Boolean>>,
    cmd2D: MutableList<List<Cmd>>
): Int {
    return runRecursive(n, curPosition, isVisited, cmd2D, 0)
}

fun runRecursive(
    limit: Int,
    curPos: Pair<Int, Int>,
    visited: List<MutableList<Boolean>>,
    cmd2D: MutableList<List<Cmd>>,
    depth: Int
): Int {
    val DEBUG: Boolean = true

    // 디버그
    if (DEBUG) {
        println("\n\r\n================")
        println("curPos = $curPos")
        //println("cmd = ${cmd2D[curPos.first][curPos.second]}")
        println("depth = $depth")
        visited.forEach { line ->
            line.forEach { elem ->
                if (elem) {
                    print("■  ")
                } else {
                    print("□  ")
                }
            }
            println()
        }
    }
    // 디버그

    //==== BEGIN 범위검사 & 보정
    if (curPos.first < 0) {
        if (DEBUG) println("ROW underflow")
        return runRecursive(
            limit,
            Pair(curPos.first + limit, curPos.second),
            visited,
            cmd2D,
            depth
        )
    }
    else if (curPos.first >= limit) {
        if (DEBUG) println("ROW overflow")
        return runRecursive(
            limit,
            Pair(curPos.first - limit, curPos.second),
            visited,
            cmd2D,
            depth
        )
    }
    else if (curPos.second < 0) {
        if (DEBUG) println("Col underflow")
        return runRecursive(
            limit,
            Pair(curPos.first, curPos.second + limit),
            visited,
            cmd2D,
            depth
        )
    }
    else if (curPos.second >= limit) {
        if (DEBUG) println("Col overflow")
        return runRecursive(
            limit,
            Pair(curPos.first, curPos.second - limit),
            visited,
            cmd2D,
            depth
        )
    }
    //==== END 범위검사 & 보정
    else if (visited[curPos.first][curPos.second]) {
        //재귀함수의 종료조건은 방문한곳에 또 방문하는 경우이다
        return depth
    }
    else {
        //==== BEGIN 다음칸 이동
        if(DEBUG) println("cmd = ${cmd2D[curPos.first][curPos.second]}")
        visited[curPos.first][curPos.second] = true
        val nextPos: Pair<Int, Int> = cmd2D[curPos.first][curPos.second].execute(curPos)
        return runRecursive(
            limit,
            nextPos,
            visited,
            cmd2D,
            depth + 1
        )
    }
}


//------------------------------------

fun isValidateRange(col: Int, row: Int, limit: Int): Boolean {
    if (!(col in 0 until limit)) {
        return false
    }
    if (!(row in 0 until limit)) {
        return false
    }
    return true
}

fun bomming(
    col: Int,
    row: Int,
    limit: Int,
    statusMap: MutableList<List<Char>>,
    valueMap: MutableList<MutableList<Int>>
) {
    if (!(isValidateRange(col, row, limit))) {
        return
    }
    val DEBUG = false

    if (DEBUG) {
        if (statusMap[row][col] == '#') {
            println("# - 아무일도 일어나지 않는다")
            return
        } else if (statusMap[row][col] == '0') {
            valueMap[row][col] = valueMap[row][col] + 1
            println("0 - [$row,$col] 이+1 > ${valueMap[row][col]} 가 되었다! ")
        } else if (statusMap[row][col] == '@') {
            valueMap[row][col] = valueMap[row][col] + 2
            println("@ - [$row,$col] 이+2 > ${valueMap[row][col]} 가 되었다! ")
        } else {
            throw RuntimeException()
        }
    } else {
        if (statusMap[row][col] == '#') {
            return
        } else if (statusMap[row][col] == '0') {
            valueMap[row][col] = valueMap[row][col] + 1
        } else if (statusMap[row][col] == '@') {
            valueMap[row][col] = valueMap[row][col] + 2
        } else {
            throw RuntimeException()
        }
    }

}

fun main0824(args: Array<String>) {
    val (N, K) = readLine()!!
        .split(" ")
        .map { it.toInt() }

    val statusMap: MutableList<List<Char>> = mutableListOf()
    val valueMap: MutableList<MutableList<Int>> = mutableListOf()
    val diffs: List<Pair<Int, Int>> = listOf(
        Pair(0, 0),
        Pair(1, 0),
        Pair(-1, 0),
        Pair(0, 1),
        Pair(0, -1)
    )
    for (i in 0 until N) {
        statusMap.add(readLine()!!
            .split(" ")
            .map { it.first() }
            .toList()
        )
        valueMap.add(Collections.nCopies(N, 0).toMutableList())
        //toMutableList() 이거 안해주면 뮤터블리스트가 아니라고 계속 런타임에러가 뜸
        // 코틀린의 컬렉션은 어질어질하네
    }

    for (i in 0 until K) {
        val (rowIn, colIn) = readLine()!!
            .split(" ")
            .map { it.toInt() }
        val col = colIn - 1
        val row = rowIn - 1

        for (diff in diffs) {
            bomming(col + diff.first, row + diff.second, N, statusMap, valueMap)
            //printMap(valueMap)
        }
    }
    print(
        valueMap.flatten()
            .maxOrNull() ?: throw NoSuchElementException("List is empty")
    )
}

fun printMap(valueMap: MutableList<MutableList<Int>>) {
    for (line in valueMap) {
        println(line)
    }
    print("\n\r\n")
}
/*

4 4
0 0 @ 0
0 0 0 0
0 # 0 0
0 0 0 @
2 2
2 3
1 4
1 4


 */

fun main0823(args: Array<String>) {
    val input = readLine()!!.toInt()
    print(
        (input / 14) +
                ((input % 14) / 7) +
                ((input % 14) % 7)
    )
}

fun main0822(args: Array<String>) {
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
        //val row = mineMap[rowIndex]
        for (colIndex in 0 until sizeOfMap) {
            //val col = row.get(colIndex)
            if (mineMap[rowIndex][colIndex] == '-') {
                mineMap[rowIndex][colIndex] =
                        //adjacentMineCount(mineMap, mineMap.indexOf(row), row.indexOf(col));
                    adjacentMineCount(mineMap, rowIndex, colIndex);
            }
        }
    }

    var answer = 0
    for (rowIndex in 0 until sizeOfMap) {
        for (colIndex in 0 until sizeOfMap) {
//            answer += when {
//                mineMap[rowIndex][colIndex] == (flag + 48).toChar() -> 1
//                else -> 0
//            }
            if (mineMap[rowIndex][colIndex] == (flag + 48).toChar()) {
                answer++
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
//            count += when {
//                isMine(row, col, mineMap) -> 1
//                else -> 0
//            }
            if (isMine(row, col, mineMap)) {
                count++
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


//==========================================================================================

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

fun getSubStringRecursive(
    beginIndex: Int,
    inputString: String,
    subStrings: MutableSet<String>
) {
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
