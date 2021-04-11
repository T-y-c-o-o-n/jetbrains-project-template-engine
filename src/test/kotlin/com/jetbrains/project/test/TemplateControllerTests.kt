package com.jetbrains.project.test

import com.jetbrains.project.test.dto.TemplateDTO
import com.jetbrains.project.test.controller.TemplateController
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.mock.http.server.reactive.MockServerHttpRequest.get
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class TemplateControllerTests(@Autowired val mockMvc: MockMvc) {

    @MockBean
    private lateinit var templateController: TemplateController

    @Test
    fun `List Templates`() {
        val templateDTO1 = TemplateDTO(
            "templateID1",
            "simple text with 1 \$var\$",
            mutableListOf("http://localhost8081", "http://localhost8082")
        )
        val templateDTO2 = TemplateDTO(
            "internshipRequest",
            "Jetbrains Internship in \$teamName\$ team.",
            mutableListOf(
                "http://some.server.url/endpoint",
                "http://some.other.url/endpoint"
            )
        )
        templateController.save(templateDTO1)
        templateController.save(templateDTO2)
        mockMvc
//            .perform(get("/api/1/template/").accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk)
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("\$.[0].author.login").value(juergen.login))
//            .andExpect(jsonPath("\$.[0].slug").value(spring5Article.slug))
//            .andExpect(jsonPath("\$.[1].author.login").value(juergen.login))
//            .andExpect(jsonPath("\$.[1].slug").value(spring43Article.slug))
    }
}