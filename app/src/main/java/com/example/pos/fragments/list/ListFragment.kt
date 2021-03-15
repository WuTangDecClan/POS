package com.example.pos.fragments.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pos.R
import com.example.pos.viewmodel.POSViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class listFragment : Fragment() {

    private lateinit var posViewModel: POSViewModel /* Storing the View Model in variable posViewModel. */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /* Inflating the layout for this fragment. */
        val view = inflater.inflate(R.layout.fragment_list, container, false) /* Setting the layout to fragment_list.xml */

        /* Creating the adapter and the RecyclerView */
        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext()) /* Stating that the Layout Manager is Linear, meaning it is a scroll down RecyclerView. */

        /* CustomerViewModel created reading all the data. */
        posViewModel = ViewModelProvider(this).get(POSViewModel::class.java)
        posViewModel.readAllData.observe(viewLifecycleOwner, Observer {customer ->
            adapter.setData(customer)
        })

        /* If the User clicks on the "Add Button" then Navigation Library changes to AddFragment.kt. */
        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return view /* Expecting View to be returned, returning fragment_list. */
    }

    /* Printing onStart() to Logcat. */
    override fun onStart() {
        super.onStart()
        Log.i("", "OnStart: ListFragment.\n")
    }

    /* Printing onResume() to Logcat. */
    override fun onResume() {
        super.onResume()
        Log.i("", "OnResume: ListFragment.\n")
    }

    /* Printing onPause() to Logcat. */
    override fun onPause() {
        super.onPause()
        Log.i("", "OnPause: ListFragment.\n")
    }

    /* Printing onStop() to Logcat. */
    override fun onStop() {
        super.onStop()
        Log.i("", "OnStop: ListFragment.\n")
    }

    /* Printing onDestroy() to Logcat. */
    override fun onDestroy() {
        super.onDestroy()
        Log.i("", "OnDestroy: ListFragment.\n")
    }

} /* Ending Fragment. */
