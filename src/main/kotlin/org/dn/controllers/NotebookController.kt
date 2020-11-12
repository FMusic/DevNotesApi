package org.dn.controllers

import org.dn.errors.NotebookNotFoundException
import org.dn.model.Notebook
import org.dn.repos.NotebookRepository
import org.dn.repos.SectionRepository
import org.springframework.web.bind.annotation.*

@RestController
class NotebookController(var notebookRepository: NotebookRepository, var sectionRepository: SectionRepository) {

    @GetMapping("/notebooks")
    fun all(): List<Notebook> = notebookRepository.findAll()

    @GetMapping("/notebooks/{id}")
    fun one(id:Long):Notebook = notebookRepository.findById(id).orElseThrow{NotebookNotFoundException(id)}

    @PostMapping("/notebooks")
    fun newNotebook(@RequestBody notebook: Notebook): Notebook = this.notebookRepository.save(notebook)

    @PutMapping ("/notebooks/{id}")
    fun replaceEmployee(@RequestBody notebook: Notebook, @PathVariable id:Long): Notebook{
        return this.notebookRepository.findById(id).map {
            it.desc = notebook.desc
            it.name = notebook.name
            this.notebookRepository.save(it)
        }.orElseThrow {NotebookNotFoundException(id)}
    }

    @DeleteMapping("/notebooks/{id}")
    fun deleteNotebook(@PathVariable id:Long){
        notebookRepository.deleteById(id)
    }
}