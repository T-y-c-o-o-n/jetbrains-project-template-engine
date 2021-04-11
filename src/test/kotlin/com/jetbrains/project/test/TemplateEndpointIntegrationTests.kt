package com.jetbrains.project.test

import com.google.gson.Gson
import com.jetbrains.project.test.dto.TemplateDTO
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class TemplateEndpointIntegrationTests(
        @Autowired val mockMvc: MockMvc) {

    @Test
    fun `List templates`() {
        val templateDTO = TemplateDTO("internshipRequest",
                "JetBrains Internship in \$teamName\$ team.",
                mutableListOf("http://localhost:8081/some.server.url/endpoint-1",
                        "http://localhost:8081/some.server.url/endpoint-2")
        )
        val gson = Gson()
        mockMvc.perform(post("/api/1/template").content(gson.toJson(templateDTO)))
                .andExpect(status().isOk)
        mockMvc.perform(get("/api/1/template").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.[0].templateId").value(templateDTO.templateId))
                .andExpect(jsonPath("\$.[0].template").value(templateDTO.template))
                .andExpect(jsonPath("\$.[0].recipients[0]").value(templateDTO.recipients[0]))
                .andExpect(jsonPath("\$.[0].recipients[0]").value(templateDTO.recipients[0]))
    }
}