package goormton

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val inputs = readLine()!!

    val chars = inputs.toCharArray().toList()
    val pointMapper: Map<String, Int> = getPointMapper(chars);


    for (first in 1 until chars.size - 2) {
        for (second in first + 1 until chars.size-1) {
            val section1: String = getSubString(0, first, chars)
            val section2: String = getSubString(first, second, chars)
            val section3: String = getSubString(second, chars.size-1, chars)
        }
    }


}

fun getSubString(beginIndex: Int, endIndex: Int, chars: List<Char>): String {
    return chars.subList(beginIndex, endIndex)
        .toCharArray()
        .contentToString()
}

fun getPointMapper(chars: List<Char>): Map<String, Int> {
    val subStrings: MutableSet<String> = mutableSetOf()
    getSubStringRecursive(0, chars, subStrings)
    //셋에 저장된거 가지고 사전순정렬 때리고 맵으로 리턴
    val stringList = subStrings.
    sortedWith{
        str1, str2 -> val result = str1.compareTo(str2) // 1) 문자열 사전순 정렬

        if (result != 0) {
            result // If strings are not equal, return the lexicographical comparison result
        } else {
            // 2) If strings are equal, sort by length in ascending order
             str2.length - str1.length
        }
        //1) 문자열 사전순 정렬
        //2) 길이가 짧은게 먼저 나오도록
    }

    /*
                compareByDescending<Int> { countOf1AtBinary(it) }
            .thenByDescending { it }
     */
    print(stringList)
    return mapOf()
}

fun getSubStringRecursive(beginIndex: Int, chars: List<Char>, subStrings: MutableSet<String>) {
    if(beginIndex > chars.size) {
        return
    }
    for (step in 1 until chars.size-1) {
        if((beginIndex + step ) > chars.size ) {
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
