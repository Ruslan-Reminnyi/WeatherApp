package com.mycompany.myapplication.workers

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mycompany.myapplication.R
import com.mycompany.myapplication.viewmodels.MainViewModel

class LoginFragment : Fragment() {

    companion object {
        const val imageUrl = "https://picsum.photos/200"
    }

    private val mainViewModel: MainViewModel by viewModels()
    private var imageView: ImageView? = null
    private var editTextLogin: EditText? = null
    private var editTextPassword: EditText? = null
    private var buttonLogin: Button? = null
    private var buttonPassword: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        initGui(view)
        initListeners()
        return view
    }

    override fun onResume() {
        super.onResume()
        setLogo()
    }

    private fun checkInput() {

        val email = editTextLogin?.text.toString()
        val password = editTextPassword?.text.toString()

        val isCorrect = mainViewModel.checkLoginInput(email, password)

        if(isCorrect) {
            changeFragment(MainFragment())
        } else {
            showValidationError()
        }

    }

    private fun showValidationError() {
        Toast.makeText(activity, getString(R.string.wrong_login_or_password), Toast.LENGTH_LONG).show()
    }

    private fun changeFragment(fragment: Fragment) {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container_view, fragment)
            addToBackStack(null)
        }
    }

    private fun setLogo() {
        imageView?.let {
            Glide.with(requireActivity())
                .load(Uri.parse(imageUrl))
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(it)
        }
    }

    private fun initGui(view: View) {
        imageView = view.findViewById<ImageView>(R.id.image)
        editTextLogin = view.findViewById<EditText>(R.id.edit_text_login)
        editTextPassword = view.findViewById<EditText>(R.id.edit_text_password)
        buttonLogin = view.findViewById<Button>(R.id.button_login)
        buttonPassword = view.findViewById<Button>(R.id.button_signup)
    }

    private fun initListeners() {
        buttonLogin?.setOnClickListener {
            Log.i("TAG", "weatherList1 ${mainViewModel.weatherList.value}")
            checkInput()
        }

        buttonPassword?.setOnClickListener {

            Log.i("TAG", "newWeatherList2 ${mainViewModel.weatherList.value}")

            changeFragment(SignUpFragment())
        }
    }

}