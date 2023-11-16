package com.example.roompractice.ui.list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.roompractice.data.local.model.User
import com.example.roompractice.databinding.CustomRowBinding

class ListAdapter(private val updateUser: (user: User) -> Unit) : Adapter<ListAdapter.ListViewHolder>() {

    private var userList = arrayListOf<User>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(tasks: List<User>) {
        userList.clear()
        userList.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(CustomRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(userList[position])

    }

    inner class ListViewHolder(private val binding: CustomRowBinding) : ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.apply {
                tvFirstName.text = user.firstName
                tvLastName.text = user.lastName
                tvAge.text = user.age.toString()
                tvStreetName.text = user.address?.streetName
                tvHomeNumber.text = user.address?.homeNumber.toString()
            }
            itemView.setOnClickListener {
                updateUser(user)
            }
        }
    }
}