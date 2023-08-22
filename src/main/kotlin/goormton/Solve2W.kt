package goormton

fun main(args: Array<String>) {
    readLine()!!.toInt()
    val chars =  readLine()!!.toCharArray().toList()
    val pointMapper: Map<String, Int> = getPointMapper(chars);

    var maxi = -1;

    for (first in 1 until chars.size) {
        for (second in first + 1 until chars.size) {
            val section1: String = getSubString(0, first, chars)
            val section2: String = getSubString(first, second, chars)
            val section3: String = getSubString(second, chars.size, chars)

            maxi = Math.max(
                pointMapper[section1]!! + pointMapper[section2]!! + pointMapper[section3]!!,
                maxi
            )
        }
    }
    print(maxi)
}

fun getSubString(beginIndex: Int, endIndex: Int, chars: List<Char>): String {
    return chars.subList(beginIndex, endIndex)
        .joinToString("")
}

fun getPointMapper(chars: List<Char>): Map<String, Int> {
    val subStrings: MutableSet<String> = mutableSetOf()
    getSubStringRecursive(0, chars, subStrings)
    val stringList: List<String> = subStrings
        .sorted()


    return stringList.associateWith { it -> stringList.indexOf(it) + 1 }
}

fun getSubStringRecursive(beginIndex: Int, chars: List<Char>, subStrings: MutableSet<String>) {
    if (beginIndex > chars.size) {
        return
    }
    for (step in 1 until chars.size - 1) {
        if ((beginIndex + step) > chars.size) {
            return
        }
        subStrings.add(getSubString(beginIndex, beginIndex + step, chars))
        getSubStringRecursive(beginIndex + step, chars, subStrings)
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
        """
