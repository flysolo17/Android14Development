package com.jmballangca.basics

import java.time.Year
data class Coffee(val name : String) {

    init {
        println(name)
    }

    fun print() {
        print(name)
    }
}
fun main() {
    val myBook = Book()
    myBook.printBook()
    val custom = Book("custom","JM", Year.parse("2000"))
    custom.printBook()
    hiBook(custom)
    val coffee = Coffee("Cappucino")
    coffee.print()
}

fun hiBook(book: Book) {
    book.printBook()
}