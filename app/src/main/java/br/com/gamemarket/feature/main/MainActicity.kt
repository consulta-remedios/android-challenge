package br.com.gamemarket.feature.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.gamemarket.R
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), MainContract.View {

    override val presenter by inject<MainContract.Presenter> { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
        presenter.test()
    }

    override fun test() {
        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show()
    }

    private fun setupViews() {

    }
}