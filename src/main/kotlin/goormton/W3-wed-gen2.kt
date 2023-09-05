package goormton

import java.util.LinkedList
import java.util.Queue

val diffs: List<Pair<Int, Int>> = listOf(
    Pair(1, 0),
    Pair(-1, 0),
    Pair(0, 1),
    Pair(0, -1)
)
var count = 0

//0829
fun main(args: Array<String>) {
    val (N,K) = readLine()!!.split(" ").map { it.toInt() }
    val matrix: MutableList<MutableList<Int>> = readMatrix(N)
    val visited: MutableList<MutableList<Boolean>> = MutableList(N) { MutableList(N) { false } }
    val scores: MutableList<Int> = MutableList(33) { 0 }

    for(fi in 0 until  N) {
        for(si in 0 until  N) {
            if(!visited[fi][si]) {
                //doBfs(fi, si, typeToSizeMap, visited)
                val q : Queue<Pair<Int,Int>> = LinkedList()
                q.add(Pair(fi, si))
                visited[fi][si] = true
                var size =1
                val typeOfBuilding = matrix[fi][si]
                while(q.isNotEmpty()) {
                    val current = q.poll()!!
                    for(step in diffs) {
                        val nextFirst = step.first + current.first
                        val nextSecond = step.second + current.second
                        if((nextFirst in 0 until N) && (nextSecond in 0 until N)) {
                            if(!visited[nextFirst][nextSecond] && (matrix[nextFirst][nextSecond]==typeOfBuilding)) {
                                visited[nextFirst][nextSecond] = true
                                q.add(Pair(nextFirst,nextSecond))
                                size++
                            }
                        }
                    }
                }
                if(size>=K) {
                    scores[typeOfBuilding]++;
                }
            }
        }
    }
    var maxTypeOfBuilding = 0
    for(i in 0 until scores.size) {
        if (scores[i] >= scores[maxTypeOfBuilding]) {
            maxTypeOfBuilding = i
        }
    }
    println(maxTypeOfBuilding)
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
