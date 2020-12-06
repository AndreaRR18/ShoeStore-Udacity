package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailFragmentBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodel.ShoeDetailViewModel
import com.udacity.shoestore.viewmodel.ShoeListViewModel

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: ShoeDetailFragmentBinding
    private val viewModelList: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.shoe_detail_fragment,
            container,
            false
        )
        binding.viewModel = ShoeDetailViewModel()

        binding.buttonAdd.setOnClickListener {
            val shoe: Shoe? = binding.viewModel!!.tryGetShoe()
            if (shoe != null) {
                addNewShoeAndGoBack(shoe)
            } else {
                Toast
                    .makeText(context, getString(R.string.toast_error_message_shoe_detail), Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.buttonCancel.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    fun addNewShoeAndGoBack(shoe: Shoe) {
        viewModelList.addNewElement(shoe)
        findNavController().popBackStack()
    }

}