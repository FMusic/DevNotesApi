package org.dn

import org.dn.model.Notebook
import org.dn.model.Section
import org.springframework.data.jpa.repository.JpaRepository

interface NotebookRepository : JpaRepository<Notebook, Long>
interface SectionRepository: JpaRepository<Section, Long>

/* for Todo stuff
interface TodoRepository: JpaRepository<Todo, Long>
 */