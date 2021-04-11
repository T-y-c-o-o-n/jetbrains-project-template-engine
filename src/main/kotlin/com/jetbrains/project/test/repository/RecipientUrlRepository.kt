package com.jetbrains.project.test.repository

import com.jetbrains.project.test.domain.RecipientUrl
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RecipientUrlRepository: CrudRepository<RecipientUrl, Long>