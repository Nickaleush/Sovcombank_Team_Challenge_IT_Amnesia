package com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.registration

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import com.example.sovkombank_team_challenge_it_amnezia.widgets.countryPicker.CCPicker
import com.example.sovkombank_team_challenge_it_amnezia.widgets.countryPicker.adapter.CountryPickerAdapter
import com.example.sovkombank_team_challenge_it_amnezia.App
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseFragment
import com.example.sovkombank_team_challenge_it_amnezia.widgets.countryPicker.model.Country
import kotlinx.android.synthetic.main.registration_fragment.*
import java.text.SimpleDateFormat
import java.util.*
import android.view.View.OnFocusChangeListener
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.UserToSignUp
import javax.inject.Inject

class RegistrationFragment : BaseFragment<RegistrationPresenterImpl>(), RegistrationView, DatePickerDialog.OnDateSetListener {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    var isKeyboardOpened = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.registration_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.view = this
        presenter.start()
        setKeyboardListener()
        setOnClickListeners()

        countryCodeTextView.setOnClickListener {
            CCPicker.showPicker(
                requireActivity(),
                object : CountryPickerAdapter.OnCountrySelectedListener {
                    override fun onCountrySelected(country: Country?) {
                        val countryCode = country?.countryCode
                        countryCodeTextView.setText(countryCode)
                    }
                })
        }

        toolbarRegistration.setNavigationOnClickListener { onBackPressed() }

    }

    @SuppressLint("CheckResult")
    private fun signUp(phone: String, birthDay:String, name: String, surname:String, lastName:String, password: String) {
        USER_PHONE = phone
        USER_NAME = name

        val confirmPassword = editTextConfirmPassword.text.toString().trim()
        val user = UserToSignUp(birthDay, phone,name, surname , lastName, password)

        if (phone.isEmpty()) {
            Toast.makeText(requireContext(), resources.getText(R.string.EnterName), Toast.LENGTH_LONG).show()
        }
        if (name.isEmpty()) {
            Toast.makeText(requireContext(), resources.getText(R.string.EnterName), Toast.LENGTH_LONG).show()
        }
        if (surname.isEmpty()) {
            Toast.makeText(requireContext(), resources.getText(R.string.EnterSurname), Toast.LENGTH_LONG).show()
        }

        if (lastName.isEmpty()) {
            Toast.makeText(requireContext(), resources.getText(R.string.EnterLastName), Toast.LENGTH_LONG).show()
        }

        if (password.isEmpty()) {
            Toast.makeText(requireContext(), resources.getText(R.string.EnterPassword), Toast.LENGTH_LONG).show()
        }

        if (confirmPassword.isEmpty()) {
            Toast.makeText(requireContext(), resources.getText(R.string.ConfirmPasswordEmpty), Toast.LENGTH_LONG).show()
        }

        if (password == confirmPassword &&  phone.isNotEmpty() && name.isNotEmpty()
            && surname.isNotEmpty()  && lastName.isNotEmpty()  && password.isNotEmpty()) {
            presenter.signUpClient(user)
        } else {
            Toast.makeText(requireContext(), resources.getText(R.string.PasswordsDontMatch), Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    override fun createComponent() {
        App.instance
            .getAppComponent()
            .createRegistrationFragment()
            .inject(this)
    }

    private fun setOnClickListeners() {
        birthDayTextView.setOnClickListener {
            getDateTimeCalendar()
            DatePickerDialog(requireContext(), this, year, month, day).show()
        }

        buttonNextRegistration.setOnClickListener {
//            presenter.savePetShortForm(sendShortPetInfo())
        }

        editTextSurname.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextLastName.callOnClick()
            }
        }

        editTextSurname.setOnClickListener {
            isKeyboardOpened = true
        }

        editTextName.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextName.callOnClick()
            }
        }
        editTextName.setOnClickListener {
            isKeyboardOpened = true
        }

        editTextLastName.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextLastName.callOnClick()
            }
        }

        editTextLastName.setOnClickListener {
            isKeyboardOpened = true
        }

        editTextPassword.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextPassword.callOnClick()
            }
        }

        editTextPassword.setOnClickListener {
            isKeyboardOpened = true
        }

        editTextConfirmPassword.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                editTextLastName.callOnClick()
            }
        }

        editTextConfirmPassword.setOnClickListener {
            isKeyboardOpened = true
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDateTimeCalendar() {
        val cal: Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month+1
        savedYear = year
        getDateTimeCalendar()
        resultMonth = if (savedMonth <10) "0${savedMonth}" else "$savedMonth"
        resultDay = if(savedDay <10) "0${savedDay}" else "$savedDay"
        dateTime = "${savedYear}-${savedMonth}-${savedDay}"
        val outputFormat= SimpleDateFormat("dd MMMM yyyy")
        val inputFormat = SimpleDateFormat("yyyy-MM-dd")
        val date= inputFormat.parse(dateTime)
        SEND_DATE_FORMAT = inputFormat.format(date)
        val formattedDate = outputFormat.format(date)
        birthDayTextView.text = formattedDate
    }

    private fun setKeyboardListener() {
        val activityRootView: View = requireActivity().window.decorView.findViewById(android.R.id.content)
        activityRootView.viewTreeObserver.addOnGlobalLayoutListener {
            val heightDiff = activityRootView.rootView.height - activityRootView.height
            if (heightDiff > 100) {
                if (isKeyboardOpened) {
                    with(scroll_view_registration) {
                        isKeyboardOpened = false
                        smoothScrollBy(0, buttonNextRegistration.y.toInt())
                    }
                }
            }
        }
    }

    override fun showError(message: String?): Unit = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

    companion object {
        var USER_PHONE = "null"
        var USER_NAME = "null"
        var USER_LASTNAME = "null"
        var year = 0
        var month = 0
        var day = 0
        var savedYear = 0
        var savedMonth = 0
        var savedDay = 0
        var resultMonth = ""
        var resultDay = ""
        var dateTime = ""
        var SEND_DATE_FORMAT = ""
    }
}