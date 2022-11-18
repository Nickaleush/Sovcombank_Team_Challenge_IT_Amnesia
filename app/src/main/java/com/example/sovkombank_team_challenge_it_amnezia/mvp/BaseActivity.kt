package com.example.sovkombank_team_challenge_it_amnezia.mvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<P : BasePresenter> : AppCompatActivity(), BaseView {

    @Inject
    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createComponent()
    }

    protected abstract fun createComponent()

    override fun onDestroy() {
        super.onDestroy()
        presenter.stop()
    }

}