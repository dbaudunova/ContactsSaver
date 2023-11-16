package com.example.roompractice.ui.add

import android.text.Editable
import android.text.TextUtils
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.roompractice.core.ui.base.BaseFragment
import com.example.roompractice.data.local.model.Address
import com.example.roompractice.data.local.model.User
import com.example.roompractice.ui.UserViewModel
import com.example.roompractice.databinding.FragmentAddBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddFragment : BaseFragment<FragmentAddBinding>() {

    private val viewModel: UserViewModel by viewModel()

    override fun initListener() {
        binding.btnAdd.setOnClickListener {
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val age = binding.etAge.text
        val streetName = binding.etStreetName.text.toString()
        val homeNumber = binding.etHomeNumber.text

        if (inputCheck(firstName, lastName, age, streetName, homeNumber)) {
            viewModel.addUser(
                User(
                    null,
                    firstName,
                    lastName,
                    Integer.parseInt(age.toString()),
                    Address(streetName, Integer.parseInt(homeNumber.toString()))
                )
            )
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
    }

    private fun inputCheck(
        firstName: String,
        lastName: String,
        age: Editable,
        streetName: String,
        homeNumber: Editable
    ): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty() && TextUtils.isEmpty(
            streetName
        ) && homeNumber.isEmpty())
    }


    override fun inflateViewBinding(): FragmentAddBinding {
        return FragmentAddBinding.inflate(layoutInflater)
    }
}