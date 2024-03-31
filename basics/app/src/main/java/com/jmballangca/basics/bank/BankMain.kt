package com.jmballangca.basics.bank

fun  main() {
    val account = BankAccount("John Mark", balance = 2000.00)
    account.deposit(5000.00)
    account.withdraw(3000.00)
    account.displayTransaction()
    println("${account.accountHolder}'s balance is $${account.accBalance()}")
}