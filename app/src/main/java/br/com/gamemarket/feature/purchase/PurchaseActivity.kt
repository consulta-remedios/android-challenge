package br.com.gamemarket.feature.purchase

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.com.gamemarket.R
import br.com.gamemarket.feature.game.GameActivity
import br.com.gamemarket.feature.main.MainActivity
import kotlinx.android.synthetic.main.activity_purchase.*
import kotlinx.android.synthetic.main.toolbar_cart.view.*

class PurchaseActivity : AppCompatActivity() {

    companion object {
        fun startPurchaseActivity(context: Context) {
            val intent = Intent(context, PurchaseActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase)

        setupViews()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setupViews() {
        setSupportActionBar(purchaseToolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        purchaseToolbar.tcTxtTitle.setText(R.string.purchase_title)
        purchaseToolbar.tcImgCart.visibility = View.GONE
        purchaseToolbar.tcImgSearch.visibility = View.GONE

        backToPurchase.setOnClickListener {
            MainActivity.startMainActivity(this)
        }
    }
}