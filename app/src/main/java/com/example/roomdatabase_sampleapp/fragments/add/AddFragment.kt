package com.example.roomdatabase_sampleapp.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdatabase_sampleapp.R
import com.example.roomdatabase_sampleapp.model.User
import com.example.roomdatabase_sampleapp.viewmodel.UserViewModel

class AddFragment : Fragment() {

    private lateinit var mUserViewModel : UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        view.findViewById<Button>(R.id.add_btn).setOnClickListener{
            insertDataToDatabase(view);
        }
        return view
    }

    private fun insertDataToDatabase(view :View) {

        val firstNames = view.findViewById<EditText>(R.id.firstName_edt).text.toString()
        val lastNames = view.findViewById<EditText>(R.id.lastName_edt).text.toString()
        val age = view.findViewById<EditText>(R.id.age_edt).text

        try {

            if(inputCheck(firstNames,lastNames, age))
            {
                //create user object
                val user = User(0,firstNames,lastNames,Integer.parseInt(age.toString()) )
                //add data to database
                mUserViewModel.addUser(user)
                Toast.makeText(requireContext(),"Successfully added",Toast.LENGTH_LONG).show()
                //navigate to list fragment from add to list fragment
                findNavController().navigate(R.id.action_addFragment_to_listFragment)
            }
        }
        catch (e:Exception)
        {
            Toast.makeText(requireContext(),"Please fill all the fields",Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(firstname:String, lastName:String, age: Editable):Boolean {
        return !(TextUtils.isEmpty(firstname) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}