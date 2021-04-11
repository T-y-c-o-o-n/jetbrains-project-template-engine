package com.jetbrains.project.test.controller

import com.jetbrains.project.test.dto.MessageDTO
import com.jetbrains.project.test.dto.TemplateDTO
import com.jetbrains.project.test.dto.TemplateVariablesDTO
import com.jetbrains.project.test.service.TemplateService
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("api/1/template")
class TemplateController(private val templateService: TemplateService) {

    @GetMapping
    fun findAll() = templateService.findAll()

    @PostMapping
    fun save(@RequestBody templateDTO: TemplateDTO) {
        templateService.save(templateDTO);
    }

    @PostMapping("/sent")
    fun sent(@RequestBody templateVariablesDTO: TemplateVariablesDTO) {
        val template = templateService.find(templateVariablesDTO.templateId)
        val message = templateService.fillTemplate(template, templateVariablesDTO)

        val restTemplate = RestTemplate()
        for (recipientUrl in template.recipients) {
            restTemplate.postForLocation(recipientUrl.url, MessageDTO(message))
        }
    }
}