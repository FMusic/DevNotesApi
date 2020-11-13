package org.dn.controllers

import org.dn.errors.NotebookNotFoundException
import org.dn.errors.SectionNotFoundException
import org.dn.model.Notebook
import org.dn.model.NotebookFull
import org.dn.model.Section
import org.dn.repos.NotebookRepository
import org.dn.repos.SectionRepository
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
class NotebookController(var notebookRepository: NotebookRepository, var sectionRepository: SectionRepository) {
    /**
     * Returns all notebooks on server
     */
    @GetMapping("/notebooks")
    fun all(): List<Notebook> = notebookRepository.findAll()

    /**
     * Returns whole notebook for notebookId
     */
    @GetMapping("/notebooks/{id}")
    fun one(@PathVariable id:Long):NotebookFull {
        val notebook = notebookRepository.findById(id).orElseThrow{NotebookNotFoundException(id)}
        return NotebookFull(
                notebook,
                sectionRepository.findAll()
                        .stream()
                        .filter{x->x.notebook == notebook}
                        .collect(Collectors.toList())
        )
    }

    /**
     * Creates new notebook
     */
    @PostMapping("/notebooks")
    fun newNotebook(@RequestBody notebook: Notebook): Notebook = this.notebookRepository.save(notebook)

    /**
     * Updates or creates new notebook
     */
    @PutMapping ("/notebooks/{id}")
    fun updateNotebook(@RequestBody notebook: Notebook, @PathVariable id:Long): Notebook{
        return this.notebookRepository.findById(id).map {
            it.desc = notebook.desc
            it.name = notebook.name
            this.notebookRepository.save(it)
        }.orElseThrow {NotebookNotFoundException(id)}
    }

    /**
     * Delete notebook for notebookId
     */
    @DeleteMapping("/notebooks/{id}")
    fun deleteNotebook(@PathVariable id:Long){
        notebookRepository.deleteById(id)
    }

    /**
     * Add new section
     */
    @PostMapping("/sections")
    fun newSection(@RequestBody section: Section) : Section = sectionRepository.save(section)

    /**
     * update text and notebook in section
     */
    @PutMapping("/sections/{id}")
    fun updateSection(@RequestBody section: Section, @PathVariable id: Long): Section =
            sectionRepository.findById(id).map {
                it.text = section.text
                it.notebook = section.notebook
                sectionRepository.save(it)
            }.orElseThrow { SectionNotFoundException(id) }

    /**
     * delete section by it's id
     */
    @DeleteMapping("/sections/{id}")
    fun deleteSection(@PathVariable id:Long) = notebookRepository.deleteById(id)
}