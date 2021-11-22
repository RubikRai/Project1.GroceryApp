package com.example.project1groceryapp.view

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.project1groceryapp.contracts.LoginView
import com.example.project1groceryapp.R
import com.example.project1groceryapp.contracts.LoginModel
import com.example.project1groceryapp.databinding.FragmentLoginBinding
import com.example.project1groceryapp.model.AuthenticationNetworkService
import com.example.project1groceryapp.presenter.LoginPresenter
import org.json.JSONObject


class LoginFragment : Fragment(), LoginView {

    lateinit var binding:FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater,container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.buttonNeedAnAccount.setOnClickListener{
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frame_layout_login,
                SignupFragment())?.addToBackStack("backToSignUp")?.commit()
        }
        super.onViewCreated(view, savedInstanceState)

        val loginPresenter = LoginPresenter(this, AuthenticationNetworkService(requireContext()))

        binding.buttonLogin.setOnClickListener{
            loginPresenter.loginUser(binding.edittextEmail.text.toString(), binding.edittextPassword.text.toString())
        }
    }


    override fun loginSuccess(firstName: String) {

        activity?.supportFragmentManager?.beginTransaction()?.replace(
            R.id.frameLayout_splash,
            HomepageFragment()
        )?.addToBackStack("goToSignUp")?.commit()

    }

    override fun loginFailed() {
        Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
    }

}