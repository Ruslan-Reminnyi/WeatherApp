package com.mycompany.myapplication.workers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.mycompany.myapplication.R
import com.mycompany.myapplication.viewmodels.MainViewModel

class SignUpFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()
    private var editTextEmail: EditText? = null
    private var editTextPassword: EditText? = null
    private var editTextConfirmPassword: EditText? = null
    private var buttonRegister: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_signup, container, false)
        initGui(view)
        initListeners()
        return view
    }
    
    private fun checkInput() {

        val email = editTextEmail?.text.toString()
        val password = editTextPassword?.text.toString()
        val confirmPassword = editTextConfirmPassword?.text.toString()

        val isCorrect = mainViewModel.checkRegistrationInput(email, password, confirmPassword)

        if(isCorrect) {
            mainViewModel.writeRegistrationData(email = email, password = password)
            changeFragment(MainFragment())
        } else {
            showValidationError()
        }

    }

    private fun showValidationError() {
        Toast.makeText(activity, getString(R.string.wrong_input), Toast.LENGTH_LONG).show()
    }

    private fun changeFragment(fragment: Fragment) {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container_view, fragment)
            addToBackStack(null)
        }
    }

    fun initGui(view: View) {
        editTextEmail = view.findViewById<EditText>(R.id.edit_text_email)
        editTextPassword = view.findViewById<EditText>(R.id.edit_text_password)
        editTextConfirmPassword = view.findViewById<EditText>(R.id.edit_text_confirm_password)
        buttonRegister = view.findViewById<Button>(R.id.button_register)
    }

    fun initListeners() {
        buttonRegister?.setOnClickListener {
            checkInput()
        }
    }


}