package br.com.gamemarket.feature.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.gamemarket.R
import br.com.gamemarket.base.extensions.loadImage
import br.com.gamemarket.base.extensions.toCurrency
import br.com.gamemarket.data.model.ItemCart
import kotlinx.android.synthetic.main.item_cart.view.*

class CartAdapter: RecyclerView.Adapter<CartAdapter.Holder>()  {

    var data: MutableList<ItemCart> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var onItemListener: (item:ItemCart) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_cart,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.render(data[position])
    }

    fun setOnChangeListener(listener:(item:ItemCart) -> Unit) {
        onItemListener = listener
    }

    fun deleteItem(position: Int) {
        onItemListener(data[position])
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun render(item: ItemCart) {
            itemView.igImgCover.loadImage(item.image)
            itemView.igTxtPlatform.text = item.platform
            itemView.igTxtName.text = item.name
            itemView.igTxtPrice.text = (item.quantity * item.price).toCurrency()
            itemView.gameQtd.text = item.quantity.toString()
        }
    }
}