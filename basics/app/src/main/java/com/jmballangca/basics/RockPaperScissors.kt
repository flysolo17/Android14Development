package com.jmballangca.basics

fun  main() {
    print("ROCK ,PAPER or SCISSORS, ENTER YOUR CHOICE: ")
    var yourChoice = readln().uppercase()
    while (!listOf("ROCK","PAPER","SCISSORS").contains(yourChoice)) {
        print("ROCK ,PAPER or SCISSORS, ENTER YOUR CHOICE: ")
        yourChoice = readln().uppercase()
    }

    val computerChoice = computerChoice()
    val winner = whoseWinner(yourChoice.uppercase(),computerChoice)
    if (winner == "Tie") {
        println("It's a tie!")
    } else {
        println("$winner wins")
    }
}

fun whoseWinner(human : String,computer : String)  : String {

    println("HUMAN :$human")
    println("COMPUTER: $computer")
    return when {
        human == computer -> "Tie"
        human == "ROCK" && computer == "SCISSORS" -> "Human"
        human == "PAPER" && computer == "ROCK" -> "Human"
        human == "SCISSORS" && computer == "PAPER" -> "Human"
        else -> "Computer"
    }
}

fun computerChoice() : String {
    val randomNum = (1..3).random()
    return when (randomNum) {
        1 -> {
            "ROCK"
        }
        2 -> {
            "PAPER"
        }
        else -> {
            "SCISSORS"
        }
    }

}