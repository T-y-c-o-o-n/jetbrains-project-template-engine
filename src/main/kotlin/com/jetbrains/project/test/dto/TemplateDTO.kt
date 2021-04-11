package com.jetbrains.project.test.dto

class TemplateDTO(
    val templateId: String,

    val template: String,

    val recipients: MutableList<String>
)