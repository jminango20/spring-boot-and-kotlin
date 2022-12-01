package com.jminango.repositories

import com.jminango.models.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, Long?> {
}