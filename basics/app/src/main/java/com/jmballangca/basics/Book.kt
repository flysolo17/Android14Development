package com.jmballangca.basics

import java.time.Year

class Book(val title : String = "Unknown",val author : String = "Anonymous",val year : Year = Year.now()) {
     fun printBook() {
        println("$title , $author , $year")
    }


}