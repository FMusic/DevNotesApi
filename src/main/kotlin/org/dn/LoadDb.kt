package org.dn

import org.dn.model.Notebook
import org.dn.model.Section
import org.dn.repos.NotebookRepository
import org.dn.repos.SectionRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LoadDb {
    val log: Logger = LoggerFactory.getLogger(LoadDb::class.java)

    companion object SectionObj {
        var note = Notebook(0, "Notebook1", "Notebook example")
        var sec = Section(0, "Example Section ", note)
    }

    @Bean
    fun initDatabase(notebookRepo: NotebookRepository,
                     sectionRepo: SectionRepository) =
            CommandLineRunner {
                log.info("Preloading " + notebookRepo.save(note))
                log.info("Preloading " + sectionRepo.save(sec))
            }
}