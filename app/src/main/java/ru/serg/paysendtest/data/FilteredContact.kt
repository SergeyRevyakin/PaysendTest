package ru.serg.paysendtest.data

import android.text.SpannableStringBuilder

data class FilteredContact(
    override val name: SpannableStringBuilder,
    override val phone: SpannableStringBuilder
) : ContactInterface