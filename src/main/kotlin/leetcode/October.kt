package leetcode

fun main() {
    val o: October = October()
    val actual = o.getCommon(intArrayOf(1, 2, 3), intArrayOf(2, 4))
    println(actual)
}

class October {
    fun getCommon(nums1: IntArray, nums2: IntArray): Int {
        val set = nums1.toHashSet()
        return nums2
            .firstOrNull {
                set.contains(it)
            } ?: -1
    }
}


