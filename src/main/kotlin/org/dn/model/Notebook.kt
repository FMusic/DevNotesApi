package org.dn.model

import javax.persistence.*

@Entity
data class Notebook(
        @Id
        @GeneratedValue
       val id: Long = 0L,
        var name: String = "",
        var desc: String = "",
        @ManyToMany
        var sections: List<Section>
)
@Entity
data class Section(
        @Id
        @GeneratedValue
        val id: Long = 0L,
        var text: String
)