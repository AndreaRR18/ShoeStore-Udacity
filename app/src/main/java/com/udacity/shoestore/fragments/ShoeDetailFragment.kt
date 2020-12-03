package com.udacity.shoestore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoeDetailFragmentBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodel.ShoeListViewModel
import kotlinx.android.synthetic.main.shoe_detail_fragment.view.*

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: ShoeDetailFragmentBinding
    private val viewModel: ShoeListViewModel by activityViewModels()

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

        binding.buttonAdd.setOnClickListener {
            val size: Double? = binding.shoeSizeEditText.text as? Double
            val shoe = Shoe(
                name = binding.shoeNameEditText.text.toString(),
                size = size ?: 10.0,
                company = binding.shoeCompanyEditText.text.toString(),
                description = binding.shoeDescriptionEditText.text.toString()
            )
                addNewShoeAndGoBack(shoe)
        }

        binding.buttonCancel.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    fun addNewShoeAndGoBack(shoe: Shoe) {
        viewModel.addNewElement(shoe)
        findNavController().popBackStack()
    }

}