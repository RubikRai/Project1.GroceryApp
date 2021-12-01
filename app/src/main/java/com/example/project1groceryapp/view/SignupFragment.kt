package com.example.project1groceryapp.view

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.project1groceryapp.R
import com.example.project1groceryapp.contracts.SignUpView
import com.example.project1groceryapp.databinding.FragmentSignupBinding
import com.example.project1groceryapp.model.SignUpNetworkServices
import com.example.project1groceryapp.presenter.SignUpPresenter
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_signup.*
import org.json.JSONObject

class SignupFragment : Fragment(),SignUpView {

    lateinit var binding:FragmentSignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(layoutInflater,container, false)
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAlreadyHaveAnAccount.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frame_layout_create_account_page,
                LoginFragment())?.addToBackStack("goToLogIn")?.commit()
        }

        binding.imageviewClose.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frame_layout_create_account_page,
                StartFragment())?.addToBackStack("goToStartPage")?.commit()
        }

        val signUpPresenter = SignUpPresenter(this, SignUpNetworkServices(requireContext()))

        binding.buttonSignup.setOnClickListener{
            signUpPresenter.registerUser(binding.edittextFirstName.text.toString(),
                binding.edittextMobile.text.toString(), binding.edittextEmail.text.toString(),
                binding.edittextPassword.text.toString())
        }
    }


    override fun signUpSuccess(firstName: String, result: Boolean) {
        activity?.supportFragmentManager?.beginTransaction()?.replace(
            R.id.framelayout_main_top,
            LoginFragment()
        )?.addToBackStack("goToSignUp")?.commit()
    }

    override fun signUpFailed(){
        Toast.makeText(context, "SignUp Failed. Please try again", Toast.LENGTH_LONG).show()
    }


}