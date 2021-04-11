package com.jetbrains.project.test.repository

import com.jetbrains.project.test.domain.Template
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TemplateRepository: CrudRepository<Template, String>