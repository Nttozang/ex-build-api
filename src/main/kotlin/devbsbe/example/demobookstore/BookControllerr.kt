package devbsbe.example.demobookstore

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class Book(val id: Long, val title: String,val price: Double,val name: String) {}

@RestController
@RequestMapping("/api/books")

class BookControllerr {

    @GetMapping
    fun findAll(): List<Book> = books

    @PostMapping("/{id}")
    fun buyBook(@PathVariable id:String): String{
        val book = books.find { it.id == id.toLong() }
        return if (book != null){
            books.remove(book)
            "buy book $book success"
        }else{
            "book not found"
        }
    }

    @PostMapping
    fun addBook(@RequestBody book: Book): String{
        books.add(book)
        return "Add success"
    }

    companion object{
        private val books = mutableListOf(
            Book(123,"Kotlin in Action",200.0,"karina"),
            Book(123,"Java in Action",200.0,"chaewonn")
        )
    }
}