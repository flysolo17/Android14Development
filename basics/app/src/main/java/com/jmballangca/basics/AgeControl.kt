package com.jmballangca.basics

fun main() {
    print("Enter your age as a whole number: ")
    val age : Int = readln().toInt()
    ageChecker(age)
}

fun ageChecker(age : Int) {
    if (age in 18..39) {
        println("You can go to the club")
    } else  if (age >= 40){
        println("You cannot go into the club, please go home.")
    } else {
        println("Age not verified. Please contact support.")
    }
}