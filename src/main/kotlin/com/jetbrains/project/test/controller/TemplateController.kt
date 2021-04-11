package com.jetbrains.project.test.controller

import com.jetbrains.project.test.dto.TemplateDTO
import com.jetbrains.project.test.dto.TemplateVariablesDTO
import com.jetbrains.project.test.service.TemplateService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/1/template")
class TemplateController(private val templateService: TemplateService) {

    @GetMapping
    fun findAll() = templateService.findAll()

    @PostMapping
    fun save(@RequestBody templateDTO: TemplateDTO) {
        templateService.save(templateDTO);
    }

    @GetMapping("/sent")
    fun sent(@RequestBody templateVariablesDTO: TemplateVariablesDTO) {

    }
}