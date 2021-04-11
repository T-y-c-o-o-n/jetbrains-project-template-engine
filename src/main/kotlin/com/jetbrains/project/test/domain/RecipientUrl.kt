package com.jetbrains.project.test.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class RecipientUrl(
    @Id
    @GeneratedValue
    var id: Long,

    var url: String
)