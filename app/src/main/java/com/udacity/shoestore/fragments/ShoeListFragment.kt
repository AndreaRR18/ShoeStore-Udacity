package com.udacity.shoestore.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toolbar
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeListFragmentBinding
import com.udacity.shoestore.viewmodel.ShoeListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class ShoeListFragment : Fragment() {

    private val viewModel: ShoeListViewModel by activityViewModels()
    private lateinit var binding: ShoeListFragmentBinding
    private lateinit var toolbar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.shoe_list_fragment,
            container,
            false
        )

        requireActivity().onBackPressedDispatcher.addCallback(this) {}

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getShoes().observe(viewLifecycleOwner, Observer {
            updateShoeList()
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    fun updateShoeList() {
        viewModel.getShoes().observe(viewLifecycleOwner, Observer { shoeList ->
            shoeList.map {
                val txt: TextView = TextView(context)
                txt.height = 300
                txt.width = binding.linearLayout.width
                txt.setText("Name: ${it.name} \n " + "Size: ${it.size} \n" + "company: ${it.company} \n" + "description: ${it.description} \n")
                return@map txt
            }
                .forEach { this.binding.linearLayout.addView(it) }
        })
    }

}