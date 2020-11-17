package org.dn

import org.dn.model.Notebook
import org.dn.model.Section
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
        const val exTextJson = "{\"document\":[{\"text\":[{\"type\":\"string\",\"attributes\":{},\"string\":\"ExampleSection\"},{\"type\":\"string\",\"attributes\":{\"blockBreak\":true},\"string\":\"\\n\"}],\"attributes\":[]}],\"selectedRange\":[14,14]}"
        var sec = Section(0, "Example Section ", exTextJson, note)
    }

    @Bean
    fun initDatabase(notebookRepo: NotebookRepository,
                     sectionRepo: SectionRepository) =
            CommandLineRunner {
                log.info("Preloading " + notebookRepo.save(note))
                log.info("Preloading " + sectionRepo.save(sec))
            }
}