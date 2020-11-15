package org.dn.model

import javax.persistence.*

@Entity
data class Notebook(
        @Id
        @GeneratedValue
       val id: Long = 0L,
        var name: String = "",
        var desc: String = ""
)
@Entity
data class Section(
        @Id
        @GeneratedValue
        val id: Long = 0L,
        val name: String = "",
        var text: String,
        @ManyToOne
        var notebook: Notebook
)

data class NotebookFull(var notebook: Notebook, var sections: List<Section>)