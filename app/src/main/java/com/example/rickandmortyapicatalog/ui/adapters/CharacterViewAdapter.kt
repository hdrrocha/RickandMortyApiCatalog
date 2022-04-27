package com.example.rickandmortyapicatalog.ui.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.rickandmortyapicatalog.R
import com.example.rickandmortyapicatalog.databinding.CharacterRowItemBinding
import com.example.rickandmortyapicatalog.domain.uimodel.CharacterInfoUi
import kotlinx.android.synthetic.main.character_row_item.view.*

class CharacterViewAdapter : PagingDataAdapter<CharacterInfoUi, CharacterViewAdapter.ViewHolder>(
    CharacterInfoUiItemComparator
) {

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<CharacterRowItemBinding>(
            inflater, viewType, parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun getItemViewType(position: Int) = R.layout.character_row_item

    inner class ViewHolder(
        private val binding: CharacterRowItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        var dominantColor: Int = 0

        fun bind(item: CharacterInfoUi) {
            binding.item = item
            binding.invalidateAll()
            itemView.characterItemTitle.text = item.name

            Glide.with(itemView)
                .load(item.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        itemView.progress_circular.visibility = View.VISIBLE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        itemView.progress_circular.visibility = View.GONE
                        return false
                    }

                })
                .into(itemView.characterItemImage)

            onItemClickListener?.let { listener ->
//                itemView.setOnClickListener { listener.onItemClick(item.name) }
                //T
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(id: String)
    }

    object CharacterInfoUiItemComparator : DiffUtil.ItemCallback<CharacterInfoUi>() {
        override fun areItemsTheSame(
            oldItem: CharacterInfoUi,
            newItem: CharacterInfoUi
        ) = oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: CharacterInfoUi,
            newItem: CharacterInfoUi
        ) = oldItem == newItem
    }
}
