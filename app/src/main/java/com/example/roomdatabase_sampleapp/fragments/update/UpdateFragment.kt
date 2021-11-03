package com.example.roomdatabase_sampleapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdatabase_sampleapp.R
import com.example.roomdatabase_sampleapp.model.User
import com.example.roomdatabase_sampleapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import java.lang.Exception

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel:UserViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        //used args to get argument from list fragment to display data in edittext
        view.updatefirstName_edt.setText(args.currentUser.firstName)
        view.updatelastName_edt.setText(args.currentUser.lastName)
        view.updateage_edt.setText(args.currentUser.age.toString())

        view.update_btn.setOnClickListener {
            updateItem()
        }
        //add menu
        setHasOptionsMenu(true)
        return view
    }

    private fun updateItem()
    {
        val firstName = updatefirstName_edt.text.toString()
        val lastName = updatelastName_edt.text.toString()
        val age = Integer.parseInt(updateage_edt.text.toString())

        try
        {
            if(inputCheck(firstName, lastName, updateage_edt.text))
            {
                //create user object
                val updatedUser = User(args.currentUser.id,firstName,lastName,age)
                //update current user
                mUserViewModel.updateUser(updatedUser)
                Toast.makeText(requireContext(),"Successfully Updated",Toast.LENGTH_LONG).show()
                //Navigate back to list fragment from updatefragment to listfragment
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }
        }
        catch (e:Exception){
            Toast.makeText(requireContext(),"Please fill all the fields",Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(firstname:String, lastName:String, age: Editable):Boolean {
        return !(TextUtils.isEmpty(firstname) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        var builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(),
                "Successfully Deleted : ${args.currentUser.firstName}",
                Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){_,_ ->

        }
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")
        builder.create().show()
    }
}