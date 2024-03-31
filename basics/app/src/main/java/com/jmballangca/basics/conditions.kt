package com.jmballangca.basics

const val LEGAL_AGE = 18

fun main() {
    print("Enter Age : ")
    val enterAge = readln()
    println(enterAge.toInt().isAgeLegal())

}

fun Int.isAgeLegal() : String {
    return if (this >= LEGAL_AGE) {
        "You can go to the bar"
    } else {
        "You're too young!"
    }
}