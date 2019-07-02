package br.com.gamemarket.feature.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.gamemarket.R
import br.com.gamemarket.base.extensions.loadImage
import br.com.gamemarket.data.model.Game
import kotlinx.android.synthetic.main.item_game.view.*

class GameAdapter : RecyclerView.Adapter<GameAdapter.Holder>() {

    var data: List<Game> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var onMinusListener: (item:Game) -> Unit = {}
    private var onPlusListener: (item:Game) -> Unit = {}
    private var onItemListener: (item:Game) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_game,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.render(data[position])
    }

    fun setOnMinusClickListener(listener:(item:Game) -> Unit) {
        onMinusListener = listener
    }

    fun setOnPlusClickListener(listener:(item:Game) -> Unit) {
        onPlusListener = listener
    }

    fun setOnItemClickListener(listener:(item:Game) -> Unit) {
        onItemListener = listener
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
//            itemView.igBtnMinus.setOnClickListener {
//                onMinusListener(data[adapterPosition])
//            }
//
//            itemView.igBtnPlus.setOnClickListener {
//                onPlusListener(data[adapterPosition])
//            }

            itemView.setOnClickListener {
                onItemListener(data[adapterPosition])
            }
        }

        fun render(item: Game) {
            itemView.igImgCover.loadImage(item.image)
            itemView.igTxtTitle.text = item.name
            itemView.igTxtConsole.text = item.platform
            itemView.gamePrice.text = itemView.context.getString(R.string.item_game_price, item.price.toString())
        }
    }
}