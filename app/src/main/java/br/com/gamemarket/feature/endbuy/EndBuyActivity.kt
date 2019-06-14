package br.com.gamemarket.feature.endbuy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.com.gamemarket.R
import br.com.gamemarket.base.extensions.isVisible
import br.com.gamemarket.feature.main.MainActivity
import kotlinx.android.synthetic.main.activity_end_buy.*
import kotlinx.android.synthetic.main.toolbar_cart.view.*

class EndBuyActivity : AppCompatActivity() {

    companion object {
        fun startGameActivity(context: Context) {
            val intent = Intent(context, EndBuyActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_buy)
        setupView()
    }

    private fun setupView() {
        setupToolbar()

        tvBackToMain.setOnClickListener{
            backToMain()
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(endToolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        endToolbar.tcTxtTitle.text = resources.getString(R.string.title_end_activity)
        endToolbar.tcTxtTitle.setTextColor(resources.getColor(R.color.colorAccent))
        endToolbar.tcImgCart.isVisible = false
        endToolbar.tcImgSearch.isVisible = false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> backToMain()
            else -> super.onOptionsItemSelected(item)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun backToMain(){
        finish()
        MainActivity.startGameActivity(this)
    }
}
