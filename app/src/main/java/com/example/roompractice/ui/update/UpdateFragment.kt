package com.example.roompractice.ui.update

import android.app.AlertDialog
import android.text.Editable
import android.text.TextUtils
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.roompractice.core.ui.base.BaseFragment
import com.example.roompractice.data.local.model.Address
import com.example.roompractice.data.local.model.User
import com.example.roompractice.databinding.FragmentUpdateBinding
import com.example.roompractice.ui.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpdateFragment : BaseFragment<FragmentUpdateBinding>() {

    private lateinit var navArgs: UpdateFragmentArgs
    private val viewModel: UserViewModel by viewModel()

    override fun initListener() {
        binding.btnUpdate.setOnClickListener {
            updateData()
        }
        binding.btnDelete.setOnClickListener {
            deleteData()
        }
    }

    override fun initView() {
        super.initView()
        arguments?.let {
            navArgs = UpdateFragmentArgs.fromBundle(it)
        }

        with(binding) {
            etFirstName.setText(navArgs.currentUser.firstName)
            etLastName.setText(navArgs.currentUser.lastName)
            etAge.setText(navArgs.currentUser.age.toString())
            etStreetName.setText(navArgs.currentUser.address?.streetName)
            etHomeNumber.setText(navArgs.currentUser.address?.homeNumber.toString())
        }
    }

    private fun deleteData() {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Deleting User")
        alertDialog.setMessage("Are you sure you want\nto delete ${navArgs.currentUser.firstName}?")
        alertDialog.setPositiveButton(
            "Yes"
        ) { _, _ ->
            viewModel.deleteUser(navArgs.currentUser)
            Toast.makeText(requireContext(), "Successfully removed.", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
        alertDialog.setNegativeButton(
            "No"
        ) { dialog, _ ->
            dialog?.cancel()
        }
        alertDialog.create().show()
    }

    private fun updateData() {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val age = Integer.parseInt(binding.etAge.text.toString())
        val streetName = binding.etStreetName.text.toString()
        val homeNumber = Integer.parseInt(binding.etHomeNumber.text.toString())

        if (inputCheck(
                firstName,
                lastName,
                binding.etAge.text,
                streetName,
                binding.etHomeNumber.text
            )
        ) {
            viewModel.updateUser(
                User(
                    navArgs.currentUser.id,
                    firstName,
                    lastName,
                    age,
                    Address(streetName, homeNumber)
                )
            )
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
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

    override fun inflateViewBinding(): FragmentUpdateBinding {
        return FragmentUpdateBinding.inflate(layoutInflater)
    }
}