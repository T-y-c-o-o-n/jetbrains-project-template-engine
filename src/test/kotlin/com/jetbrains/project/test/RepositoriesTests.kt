package com.jetbrains.project.test

import com.jetbrains.project.test.domain.RecipientUrl
import com.jetbrains.project.test.domain.Template
import com.jetbrains.project.test.repository.RecipientUrlRepository
import com.jetbrains.project.test.repository.TemplateRepository
import com.jetbrains.project.test.service.TemplateService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class RepositoriesTests @Autowired constructor(
        val entityManager: TestEntityManager,
        val templateService: TemplateService,
        val recipientUrlRepository: RecipientUrlRepository
) {

    @Test
    fun `Test TemplateRepository`() {
        val recipient1 = RecipientUrl(0, "http://localhost:8081/some.server.url/endpoint-1")
        val recipient2 = RecipientUrl(0, "http://localhost:8081/some.server.url/endpoint-2")
        val template = Template("internshipRequest",
                "JetBrains Internship in \$teamName\$ team.",
                mutableListOf(recipient1, recipient2))
        entityManager.persist(recipient1)
        entityManager.flush()
        val found = recipientUrlRepository.findAll()
        assertThat(found).isNotEmpty
        assertThat(found.first()).isEqualTo(recipient1)
    }

}