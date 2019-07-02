package br.com.gamemarket.feature.cart

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import br.com.gamemarket.R
import br.com.gamemarket.base.extensions.loadImage
import br.com.gamemarket.data.model.ItemCart
import kotlinx.android.synthetic.main.item_cart.view.*

class CartAdapter : RecyclerView.Adapter<CartAdapter.Holder>() {

    var data: List<ItemCart> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var onItemListener: (item: ItemCart) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_cart,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.render(data[position])
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var spinnerArray = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        init {

            itemView.setOnClickListener {
                onItemListener(data[adapterPosition])
            }
        }

        fun render(item: ItemCart) {
            itemView.cartImgCover.loadImage(item.image)
            itemView.cartConsoleName.text = item.platform
            itemView.cartGameName.text = item.name
            itemView.cartGamePrice.text = item.price.toString()
            itemView.cartGameAmount.adapter = ArrayAdapter(itemView.context, android.R.layout.simple_spinner_item, spinnerArray)
            itemView.cartGameAmount.setSelection(item.quantity - 1)
        }
    }
}