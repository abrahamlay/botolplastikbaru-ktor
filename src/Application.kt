package com.abraham

import com.abraham.service.DatabaseHelper
import com.abraham.service.UserService
import com.abraham.web.widget
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.sessions.*
import io.ktor.auth.*
import io.ktor.gson.*
import io.ktor.features.*
import io.ktor.jackson.jackson
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.websocket.WebSockets
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    DatabaseHelper.init()
    install(DefaultHeaders)
    install(CallLogging)
    install(WebSockets)
    install(ContentNegotiation) {
        jackson { configure(SerializationFeature.INDENT_OUTPUT, true) }
    }
    install(Routing) { widget(userService = UserService()) }
}

