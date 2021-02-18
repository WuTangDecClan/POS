package com.example.pos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pos.viewmodel.POSViewModel
import com.example.pos.fragments.list.ListAdapter
import kotlinx.android.synthetic.main.fragment_list.view.*

class listFragment : Fragment() {

    private lateinit var posViewModel: POSViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)


        // RecyclerView
        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //CustomerViewModel
        posViewModel = ViewModelProvider(this).get(POSViewModel::class.java)
        posViewModel.readAllData.observe(viewLifecycleOwner, Observer {customer ->
            adapter.setData(customer)
        })


        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        return view
    }
}