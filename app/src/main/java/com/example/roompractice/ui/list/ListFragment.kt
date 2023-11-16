package com.example.roompractice.ui.list

import android.app.AlertDialog
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import com.example.roompractice.R
import com.example.roompractice.core.ui.base.BaseFragment
import com.example.roompractice.data.local.model.User
import com.example.roompractice.ui.UserViewModel
import com.example.roompractice.databinding.FragmentListBinding
import com.example.roompractice.ui.list.adapter.ListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment : BaseFragment<FragmentListBinding>(), SearchView.OnQueryTextListener {

    private val adapter = ListAdapter(this::updateUser)
    private val viewModel: UserViewModel by viewModel()

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = adapter
        binding.searchView.setOnQueryTextListener(this)
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.readAllData.observe(viewLifecycleOwner) { user ->
            adapter.setData(user)
        }
    }

    override fun initListener() {
        super.initListener()
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        binding.fabDelete.setOnClickListener {
            deleteAllUsers()
        }
    }

    private fun deleteAllUsers() {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Deleting All User")
        alertDialog.setMessage("Are you sure you want\nto delete all user?")
        alertDialog.setPositiveButton(
            "Yes"
        ) { _, _ ->
            viewModel.deleteAllUsers()
            Toast.makeText(requireContext(), "Successfully deleted.", Toast.LENGTH_SHORT).show()
        }
        alertDialog.setNegativeButton(
            "No"
        ) { dialog, _ ->
            dialog?.cancel()
        }
        alertDialog.create().show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchDatabase(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchDatabase(newText)
        }
        return true
    }

    private fun searchDatabase(query: String) {
        val searchQuery = "%${query}%"
        viewModel.searchDatabase(searchQuery).observe(viewLifecycleOwner) { list ->
            list.let {
                adapter.setData(it)
            }
        }
    }

    private fun updateUser(user: User) {
        findNavController().navigate(ListFragmentDirections.actionListFragmentToUpdateFragment(user))
    }

    override fun inflateViewBinding(): FragmentListBinding {
        return FragmentListBinding.inflate(layoutInflater)
    }
}