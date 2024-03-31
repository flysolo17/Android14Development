

fun main() {
    val unsortedList = listOf(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5)
    println("Unsorted list: $unsortedList")
    val sortedList = sortRecursiveWithDuplicates(unsortedList)
    println("Sorted list: $sortedList")
}

fun sortRecursiveWithDuplicates(list: List<Any>): List<Any> {
    if (list.size <= 1) return list

    val pivot = list[0]
    val (left, right) = list.partition { it != pivot }

    val sortedLeft = sortRecursiveWithDuplicates(left)
    val sortedRight = sortRecursiveWithDuplicates(right)

    return sortedLeft + pivot + sortedRight
}


fun <T : Comparable<T>> sortRecursive(list: List<T>): List<T> {
    if (list.size <= 1) return list

    val pivot = list[0]
    val left = list.filter { it < pivot }
    val right = list.filter { it > pivot }

    return sortRecursive(left) + pivot + sortRecursive(right)
}


fun List<Int>.sortArrUsingRecurrsion(): List<Int> {
    if (size <= 1) {
        return this
    }
    val center = size / 2
    val leftHalf = subList(0, center).sortArrUsingRecurrsion()
    val rightHalf = subList(center, size).sortArrUsingRecurrsion()

    return merge(leftHalf,rightHalf)
}

fun merge(left: List<Int>, right: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    var leftIndex = 0
    var rightIndex = 0
    while (leftIndex < left.size && rightIndex < right.size) {
        if (left[leftIndex] < right[rightIndex]) {
            result.add(left[leftIndex])
            leftIndex++
        } else {
            result.add(right[rightIndex])
            rightIndex++
        }
    }
    while (leftIndex < left.size) {
        result.add(left[leftIndex])
        leftIndex++
    }
    while (rightIndex < right.size) {
        result.add(right[rightIndex])
        rightIndex++
    }
    println("Result :${result} ")


    return result
}



fun reverse(data: String): String {
    if (data.isEmpty()) {
        return data
    }
    println(data)
    return reverse(data.substring(1)) + data[0]
}
fun String.isPalindrome() : Boolean {
    return this == this.reversed()
}
