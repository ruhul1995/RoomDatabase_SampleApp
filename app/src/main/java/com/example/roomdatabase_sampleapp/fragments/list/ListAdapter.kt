package com.example.roomdatabase_sampleapp.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase_sampleapp.R
import com.example.roomdatabase_sampleapp.model.User
import kotlinx.android.synthetic.main.custom_row.view.*

class ListAdapter :RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var userList = emptyList<User>()

    fun setData(user:List<User>)
    {
        this.userList = user
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =  LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        var currentItem  = userList[position]
        holder.idTxtView.text = currentItem.id.toString()
        holder.firstNameTxtView.text = currentItem.firstName
        holder.lastNameTxtView.text = currentItem.lastName
        holder.ageTxtView.text = currentItem.age.toString()

        holder.itemView.rowLayout.setOnClickListener {
            //with this i can share user Object
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        if(userList.isNotEmpty())
            return userList.size
        else return 0

    }
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        var idTxtView : TextView
        var firstNameTxtView : TextView
        var lastNameTxtView : TextView
        var ageTxtView : TextView

        init {
            idTxtView = itemView.findViewById(R.id.id_txtV)
            firstNameTxtView = itemView.findViewById(R.id.firstName_txtV)
            lastNameTxtView = itemView.findViewById(R.id.lastName_txtV)
            ageTxtView = itemView.findViewById(R.id.age_txtV)
        }
    }
}