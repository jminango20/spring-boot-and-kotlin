package com.jminango.services

import com.jminango.controller.BookController
import com.jminango.data.vo.v1.BookVO
import com.jminango.exceptions.RequiredObjectsIsNullException
import com.jminango.exceptions.ResourceNotFoundException
import com.jminango.mapper.DozerMapper
import com.jminango.models.Book
import com.jminango.repositories.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class BookService {

    @Autowired
    private lateinit var bookRepository : BookRepository

    private val logger = Logger.getLogger(BookService::class.java.name)

    private val mapper = DozerMapper

    fun findAll() : List<BookVO> {
        logger.info("Finding all people!")
        val listBook = bookRepository.findAll()
        val listBookVO = mapper.parseListObjects(listBook, BookVO::class.java)
        for (bookVO in listBookVO){
            val withSelfRel = linkTo(BookController::class.java).slash(bookVO.key).withSelfRel()
            bookVO.add(withSelfRel)
        }
        return listBookVO
    }

    fun findById(id: Long) : BookVO {
        logger.info("Finding Book! with id $id")
        val book = bookRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this Id $id") }
        val bookVO = mapper.parseObject(book, BookVO::class.java)
        val withSelfRel = linkTo(BookController::class.java).slash(bookVO.key).withSelfRel()
        bookVO.add(withSelfRel)
        return bookVO
    }

    fun createBook(bookVO : BookVO) : BookVO {
        logger.info("Create a Book with title ${bookVO.title}!")
        val entity : Book = bookRepository.save(mapper.parseObject(bookVO, Book::class.java))
        val bookVO = mapper.parseObject(entity, BookVO::class.java)
        val withSelfRel = linkTo(BookController::class.java).slash(bookVO.key).withSelfRel()
        bookVO.add(withSelfRel)
        return bookVO
    }

    fun updateBook(bookVO: BookVO?) : BookVO {
        logger.info("Updating a Book with Id")

        if (bookVO == null) throw  RequiredObjectsIsNullException()

        val book = mapper.parseObject(bookVO, Book::class.java)

        val entity = bookRepository.findById(book.id)
            .orElseThrow{ResourceNotFoundException("No records found for this Id")}

        entity.author = book.author
        entity.title = book.title
        entity.launchDate = book.launchDate
        entity.price = book.price

        val bookVO = mapper.parseObject(bookRepository.save(entity), BookVO::class.java)
        val withSelfRel = linkTo(BookController::class.java).slash(bookVO.key).withSelfRel()
        return bookVO.add(withSelfRel)
    }
    fun deleteBook(id : Long) {
        logger.info("Delete a Book with id ${id} !")

        val entity = bookRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this Id ${id}") }

        bookRepository.delete(entity)

    }



}