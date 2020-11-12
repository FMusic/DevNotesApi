package org.dn

import org.dn.model.Notebook
import org.dn.model.Section
import org.dn.repos.NotebookRepository
import org.dn.repos.SectionRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LoadDb{
    val log = LoggerFactory.getLogger(LoadDb::class.java)

    companion object SectionObj{
        var sec = Section(0, "Example Section ")
    }

    @Bean
    fun initDatabase(notebookRepo: NotebookRepository, sectionRepo: SectionRepository) = CommandLineRunner {
        val section = sectionRepo.save(sec)
        sec = section
        sectionRepo.flush()
        val notebook1 = Notebook(0, "Biljeznica 1", "bilj 1", listOf(sec))
        val notebook2 = Notebook(0, "Notebook 2", "notes 2", listOf(sec))
        log.info("Preloading " + notebookRepo.saveAll(listOf(notebook1, notebook2)))
    }
}