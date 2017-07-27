package com.example.administrator.kotlin.domain.commands

interface Command<out T> {
    fun execute(): T
}