package br.com.gamemarket.feature.cart

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
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
        }
    }
}