package ru.serg.paysendtest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ru.serg.paysendtest.R
import ru.serg.paysendtest.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val contactAdapter = ContactAdapter(viewModel.contactList)

        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)

        binding.contactsRv.apply {
            adapter = contactAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(divider)
        }

        contactAdapter.notifyDataSetChanged()

        binding.searchFab.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_main_to_fragment_search)
        }

        super.onViewCreated(view, savedInstanceState)
    }


}