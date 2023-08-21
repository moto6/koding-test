package goormton

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val inputs = readLine()!!

    val origin = inputs.toCharArray().toList()
    val pointMapper : Map<String,Int> = getPointMapper(inputs);


    for(fisrt in 1 until  origin.size-1) {
        for(second in fisrt+1 until origin.size) {
            val section1:String = subString(0, fisrt, origin)
            val section2:String = subString(fisrt, second, origin)
            val section3:String = subString(second, origin.size, origin)
        }
    }


}

fun subString(beginIndex: Int, endIndex: Int, chars: List<Char>): String {
    return chars.subList(beginIndex,endIndex)
        .toCharArray()
        .contentToString()
}

fun getPointMapper(input:String): Map<String, Int> {

}

val note0821: String = """
    일단 부르트포스로 가능한지 생각해봤다. 부르트포스가 가능하다면 그냥 문제에서 주어진대로 구현하면 끝날수 있기 때문
    양극단(맨앞 & 맨뒤) 지점을 제외한 두곳에 콤마를 배치하면 3개의 구간으로 문자열을 나눌수 있다.
    즉 n * (n-1) 가지 경우에 대해서 탐색을 허용한다면 브루트포스이고 이 문제의 최대 길이가 300 이기 때문에 최악의 경우 9만건정도만 계산한다면 답을 구할수 있다
    나쁘지 않은 연산횟수라 브루트 포스로 문제를 풀어보겠습니다.
        
        """
