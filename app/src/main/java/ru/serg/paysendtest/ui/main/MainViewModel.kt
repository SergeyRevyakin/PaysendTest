package ru.serg.paysendtest.ui.main

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.serg.paysendtest.data.Contact
import ru.serg.paysendtest.data.FilteredContact
import kotlin.random.Random

class MainViewModel : ViewModel() {
    private val _contactList: MutableList<Contact> = mutableListOf()

    val filteredContacts = MutableLiveData<List<FilteredContact>>()

    val contactList
        get() = _contactList

    init {
        fillContactList()
    }

    private fun fillContactList() {
        for (i in 1..1000) {
            val nameLength = Random.nextInt(3, 9)
            val surnameLength = Random.nextInt(4, 9)
            val name = ('a'..'z').map { it }.shuffled().subList(0, nameLength).joinToString("")
                .replaceFirstChar { it.uppercaseChar() }
            val surname =
                ('a'..'z').map { it }.shuffled().subList(0, surnameLength).joinToString("")
                    .replaceFirstChar { it.uppercaseChar() }
            val phone = Random.nextInt(1000000, 9999999)
            _contactList.add(Contact(name.plus(" ").plus(surname), phone.toString()))
        }
    }

    fun filterContactList(query: String) {

        filteredContacts.value = _contactList
            .filter {
                it.name.contains(query, true) ||
                        it.phone.contains(query, true)
            }
            .map {
                val spanName = SpannableStringBuilder()
                val spanPhone = SpannableStringBuilder()
                if (it.name.contains(query, true)) {
                    spanName.append(it.name)
                    spanName.setSpan(
                        ForegroundColorSpan(Color.GREEN),
                        it.name.indexOf(query, ignoreCase = true),
                        it.name.indexOf(query, ignoreCase = true) + query.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    spanPhone.append(it.phone)
                } else {
                    spanPhone.append(it.phone.substringBefore(query))
                    spanPhone.append(
                        query,
                        ForegroundColorSpan(Color.GREEN),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    spanPhone.append(it.phone.substringAfter(query))
                    spanName.append(it.name)
                }
                FilteredContact(spanName, spanPhone)
            }
    }
}