package ru.serg.paysendtest.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.serg.paysendtest.data.ContactInterface
import ru.serg.paysendtest.databinding.FragmentMainListItemBinding

class ContactAdapter(
    private val contactList: List<ContactInterface>
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    private lateinit var fragmentMainListItemBinding: FragmentMainListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        fragmentMainListItemBinding =
            FragmentMainListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(fragmentMainListItemBinding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contactList[position])
    }

    override fun getItemCount() = contactList.size

    class ContactViewHolder(private val binding: FragmentMainListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(contact: ContactInterface) {
            binding.contact = contact
        }
    }
}