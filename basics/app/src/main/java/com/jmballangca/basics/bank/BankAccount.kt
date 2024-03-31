package com.jmballangca.basics.bank


class BankAccount(var accountHolder : String, var balance : Double) {
    private val transactions  = mutableListOf<String>()

    fun deposit(amount: Double) {
        balance+=amount
        transactions.add("$accountHolder deposit $$amount")
    }
    fun withdraw(amount: Double) {
        if (amount > balance) {
            transactions.add("Insufficient balance to  withdraw $$amount")
        } else {
            balance-=amount
            transactions.add("$accountHolder withdrew $$amount")
        }
    }
    fun displayTransaction() {
        println("Transactions of $accountHolder")
        println("-------------------------------")

        for (transaction in transactions) {
            println(transaction)
        }
    }
    fun displayBalance() {
        println("$accountHolder Balance's is $ $balance")
    }

    fun accBalance() : Double {
        return balance
    }

}