package ru.serg.paysendtest.data

data class Contact(
    override var name: String,
    override var phone: String
) : ContactInterface