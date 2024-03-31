package com.jmballangca.basics

fun main() {
    println("Please enter a number: ")
    val inputString = readln().toIntOrNull()
    val multiplier = 5
    val sum = (inputString ?: 0) * multiplier
    println("Result of operation is: $sum")
}