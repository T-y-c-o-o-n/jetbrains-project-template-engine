package com.jetbrains.project.test.service

import com.jetbrains.project.test.domain.RecipientUrl
import com.jetbrains.project.test.domain.Template
import com.jetbrains.project.test.dto.TemplateDTO
import com.jetbrains.project.test.dto.TemplateVariablesDTO
import com.jetbrains.project.test.exception.TemplateNotFoundException
import com.jetbrains.project.test.repository.RecipientUrlRepository
import com.jetbrains.project.test.repository.TemplateRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class TemplateService(
        @Autowired private val templateRepository: TemplateRepository,
        @Autowired private val recipientUrlRepository: RecipientUrlRepository
) {

    fun findAll() = templateRepository.findAll()

    fun find(id: String) = templateRepository.findById(id).get()

    fun save(templateDTO: TemplateDTO) {
        templateRepository.save(
                Template(
                        templateDTO.templateId,
                        templateDTO.template,
                        templateDTO.recipients
                                .map { recipientUrlRepository.save(RecipientUrl(0, it)) }
                                .toMutableList()
                )
        )
    }

    fun fillTemplate(template: Template, templateVariablesDTO: TemplateVariablesDTO): String {
        var tempTemplate: String = template.template
        for (map: Map<String, String> in templateVariablesDTO.variables) {
            for (entry: Map.Entry<String, String> in map) {
                tempTemplate = tempTemplate.replace("$" + entry.key + "$", entry.value)
            }
        }
        return tempTemplate
    }
}