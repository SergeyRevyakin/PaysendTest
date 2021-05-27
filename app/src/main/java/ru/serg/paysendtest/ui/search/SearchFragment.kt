package ru.serg.paysendtest.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ru.serg.paysendtest.databinding.FragmentSearchBinding
import ru.serg.paysendtest.ext.gone
import ru.serg.paysendtest.ext.visible
import ru.serg.paysendtest.ui.main.ContactAdapter
import ru.serg.paysendtest.ui.main.MainViewModel

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViews()
        setupRecycler()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupRecycler() {
        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)

        viewModel.filteredContacts.observe(viewLifecycleOwner, { contactList ->
            val contactAdapter = ContactAdapter(contactList)

            if (contactList.isEmpty()) {
                binding.noContactsView.root.visible()

            } else {
                binding.noContactsView.root.gone()
            }

            binding.searchContactsRv.apply {
                visible()
                adapter = contactAdapter
                layoutManager = LinearLayoutManager(requireContext())
                addItemDecoration(divider)
            }
        })
    }

    private fun setupViews() {
        binding.backIv.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.searchInputTv.addTextChangedListener {
            if (it.isNullOrBlank()) {
                binding.searchContactsRv.gone()
            } else {
                viewModel.filterContactList(it.toString())
            }
        }

        binding.closeIv.setOnClickListener {
            binding.searchInputTv.setText("")
        }
    }

}