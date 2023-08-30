package goormton

import java.util.*


//0829
fun main(args: Array<String>) {
    val N = readLine()!!.toInt()
    val visited: MutableList<MutableList<Boolean>> = MutableList(N) { MutableList(N) { false } }
    //val visited = Array(N) { Array(N) { false } }
    val diffs: List<Pair<Int, Int>> = listOf(
        Pair(0, 1),
        Pair(1, 0),
        Pair(-1, 0),
        Pair(0, -1),
    )
    val queue: Queue<Pair<Int, Int>> = LinkedList()// 자바의 그것을 사용해야한다
    val matrix: MutableList<MutableList<Int>> = readMatrix(N)
    var count = 0


    for (fi in 0 until N) {
        for (si in 0 until N) {
            if (isHouse(matrix, fi, si) && isNeverVisited(visited, fi, si)) {
                queue.add(Pair(fi, si))
                visited[fi][si] = true
                while (queue.isNotEmpty()) {
                    val curCodi: Pair<Int, Int> = queue.poll()
                    for (step in diffs) {
                        val nextCodi = Pair(
                            curCodi.first + step.first,
                            curCodi.second + step.second
                        )
                        if (isInRange(nextCodi, N)) {
                            queue.add(nextCodi)
                            visited[nextCodi.first][nextCodi.second] = true
                            println(visited)
                        }
                    }
                }
            }
            count++ //install gen
        }
    }
    println(count)
}

fun isInRange(nextCodi: Pair<Int, Int>, size: Int): Boolean {
    return (nextCodi.first in 0 until size) &&
            (nextCodi.second in 0 until size)
}

fun isNeverVisited(
    visited: MutableList<MutableList<Boolean>>,
    firstIndex: Int,
    secondIndex: Int
): Boolean {
    return !visited[firstIndex][secondIndex]
}

fun isHouse(
    matrix: MutableList<MutableList<Int>>,
    firstIndex: Int,
    secondIndex: Int
): Boolean {
    return matrix[firstIndex][secondIndex] == 1
}

fun readMatrix(size: Int): MutableList<MutableList<Int>> {
    val matrix: MutableList<MutableList<Int>> = mutableListOf()
    for (i in 0 until size) {
        matrix.add(
            readLine()!!
                .split(" ")
                .map { it.toInt() }
                .toMutableList()
        )
    }
    return matrix
}


/*
인접한 집은 전기가 통한다 >> BFS
발전기의 최소한의 갯수를 구해라


참고로
BFS >> 큐로 도전 >> FIFO
 : 완전탐색, 방문이 가능한 모든 노드를 탐색

DFS >> 스텍 >> FILO
  : 최단경로를 구하는 방법
 */
