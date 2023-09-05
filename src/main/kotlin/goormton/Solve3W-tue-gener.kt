//package goormton
//
//import java.util.*
//
//val diffs: List<Pair<Int, Int>> = listOf(
//    Pair(1, 0),
//    Pair(-1, 0),
//    Pair(0, 1),
//    Pair(0, -1)
//)
//var count = 0
//
////0829
//fun main(args: Array<String>) {
//    val N = readLine()!!.toInt()
//    val matrix: MutableList<MutableList<Int>> = readMatrix(N)
//    //val visited: MutableList<MutableList<Boolean>> = configMatrix(N)
//    val visited: MutableList<MutableList<Boolean>> = MutableList(N) { MutableList(N) { false } }
//
//    for (fi in 0 until N) {
//        for (si in 0 until N) {
//            doBfs(fi, si, N, matrix, visited);
//        }
//    }
//    println(count)
//    //println("count = $count")
//}
//
//fun doBfs(
//    fi: Int,
//    si: Int,
//    N: Int,
//    matrix: MutableList<MutableList<Int>>,
//    visited: MutableList<MutableList<Boolean>>,
//) {
//
//    if (isHouse(matrix, fi, si) && isNotVisited(visited, fi, si)) {
//
//        val queue: Queue<Pair<Int, Int>> = LinkedList()// 자바의 그것을 사용해야한다
//        queue.add(Pair(fi, si))
//        visited[fi][si] = true
//
//        while (queue.isNotEmpty()) {
//            //printVisited(visited)
//            val curCodi = queue.poll()
//            //println(queue.size)
//            //println(curCodi)
//
//            for (step in diffs) {
//                val nextCodi = Pair(
//                    curCodi.first + step.first,
//                    curCodi.second + step.second
//                )
//                if (isInRange(nextCodi, N) &&
//                    isHouse(matrix, nextCodi.first, nextCodi.second) &&
//                    isNotVisited(visited, nextCodi.first, nextCodi.second)
//                ) {
//                    //println("GO NEXT = $nextCodi")
//                    queue.add(nextCodi)
//                    visited[nextCodi.first][nextCodi.second] = true
//                }
//            }
//        }
//        count += 1
//    }
//}
//
//fun printVisited(visited: MutableList<MutableList<Boolean>>) {
//    visited.forEach { it ->
//        it.forEach {
//            if (it) {
//                print("■  ")
//            } else {
//                print("□  ")
//            }
//        }
//        println()
//    }
//}
//
//fun configMatrix(N: Int): MutableList<MutableList<Boolean>> {
//    val s: MutableList<MutableList<Boolean>> = mutableListOf()
//    //return MutableList(N) { MutableList(N) { false } }
//    for (i in 0 until N) {
//        val ss: MutableList<Boolean> = MutableList(N) { false }
//        s.add(ss)
//    }
//    return s
//}
//
//fun isInRange(nextCodi: Pair<Int, Int>, size: Int): Boolean {
//    return (nextCodi.first in 0 until size) &&
//            (nextCodi.second in 0 until size)
//}
//
//fun isNotVisited(
//    visited: MutableList<MutableList<Boolean>>,
//    firstIndex: Int,
//    secondIndex: Int
//): Boolean {
//    return !visited[firstIndex][secondIndex]
//}
//
//fun isHouse(
//    matrix: MutableList<MutableList<Int>>,
//    firstIndex: Int,
//    secondIndex: Int
//): Boolean {
//    return matrix[firstIndex][secondIndex] == 1
//}
//
//fun readMatrix(size: Int): MutableList<MutableList<Int>> {
//    val matrix: MutableList<MutableList<Int>> = mutableListOf()
//    for (i in 0 until size) {
//        matrix.add(
//            readLine()!!
//                .split(" ")
//                .map { it.toInt() }
//                .toMutableList()
//        )
//    }
//    return matrix
//}
//
//
///*
//인접한 집은 전기가 통한다 >> BFS
//발전기의 최소한의 갯수를 구해라
//
//
//참고로
//BFS >> 큐로 도전 >> FIFO
// : 완전탐색, 방문이 가능한 모든 노드를 탐색
//
//DFS >> 스텍 >> FILO
//  : 최단경로를 구하는 방법
// */
