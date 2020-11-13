package org.dn.errors

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

class NotebookNotFoundException(id: Long)
    : RuntimeException("Could not find notebook $id") {
}

@ControllerAdvice
class NotebookNotFoundAdvice{
    @ResponseBody
    @ExceptionHandler(value = [(NotebookNotFoundException::class)])
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun notebookNotFoundHandler(ex: NotebookNotFoundException): String{
        return ex.message.toString()
    }
}

class SectionNotFoundException(id: Long) : RuntimeException("Could not find section $id"){}

@ControllerAdvice
class SectionNotFoundAdvice{
    @ResponseBody
    @ExceptionHandler(value = [(SectionNotFoundException::class)])
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun sectionNotFoundHandler(ex: SectionNotFoundException): String{
        return ex.message.toString()
    }
}