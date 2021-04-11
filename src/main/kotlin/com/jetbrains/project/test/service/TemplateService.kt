package com.jetbrains.project.test.service

import com.jetbrains.project.test.domain.RecipientUrl
import com.jetbrains.project.test.domain.Template
import com.jetbrains.project.test.dto.TemplateDTO
import com.jetbrains.project.test.dto.TemplateVariablesDTO
import com.jetbrains.project.test.exception.TemplateNotFoundException
import com.jetbrains.project.test.repository.RecipientUrlRepository
import com.jetbrains.project.test.repository.TemplateRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class TemplateService(
    private val templateRepository: TemplateRepository,
    private val recipientUrlRepository: RecipientUrlRepository
) {

    fun findAll() = templateRepository.findAll()

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

    fun fillTemplate(templateVariablesDTO: TemplateVariablesDTO): String {
        val template = templateRepository.findById(templateVariablesDTO.templateId)
            ?: throw TemplateNotFoundException(
                "template with id \'" + templateVariablesDTO.templateId
                        + "\' not found"
            )
        var tempTemplate: String = template.get().template
        for (entry: Map.Entry<String, String> in templateVariablesDTO.variables) {
            tempTemplate = tempTemplate.replace("$" + entry.key + "$", entry.value)
        }
        return tempTemplate
    }
}