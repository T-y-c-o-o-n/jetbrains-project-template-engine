package com.jetbrains.project.test.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Template(
    @Id
    var templateId: String,

    var template: String,

    @OneToMany
    var recipients: MutableList<RecipientUrl>
)
