package br.com.gamemarket.feature.cart

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.gamemarket.R
import br.com.gamemarket.feature.game.GameActivity

class CartActivity : AppCompatActivity() {

    companion object {
        fun startGameActivity(context: Context) {
            val intent = Intent(context, GameActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
    }
}
