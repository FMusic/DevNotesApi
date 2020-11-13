package org.dn.controllers

import org.dn.errors.NotebookNotFoundException
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
    fun replaceEmployee(@RequestBody notebook: Notebook, @PathVariable id:Long): Notebook{
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

    fun TODOS(){
        TODO("Sections controller")
    }
}