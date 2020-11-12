package org.dn

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DevnotesapiApplication

fun main(args: Array<String>) {
	runApplication<DevnotesapiApplication>(*args)
}
