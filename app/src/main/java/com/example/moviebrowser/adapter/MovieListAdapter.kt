package com.example.ubbassignment2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import coil.load
import coil.size.Scale
import com.example.ubbassignment2.R
import com.example.ubbassignment2.databinding.MovieItemBinding
import com.example.ubbassignment2.model.ThinMovie
import com.example.ubbassignment2.viewholder.MovieViewHolder
import com.google.android.material.chip.Chip

class MovieListAdapter(val onItemClickListener: (item: ThinMovie) -> Unit) :
    ListAdapter<ThinMovie, MovieViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val viewHolder = MovieViewHolder(
            MovieItemBinding.inflate(
                layoutInflater, parent, false
            )
        )
        viewHolder.binding.root.setOnClickListener {
            onItemClickListener(getItem(viewHolder.adapterPosition))
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item: ThinMovie = getItem(position)
        val binding: MovieItemBinding = holder.binding
        binding.movieTitle.text = item.title
        binding.yearText.text = item.year
        binding.languageText.text = item.lang
        binding.rating.text = item.rating.toString()
        binding.imageView.load(
            item.imageUrl
        )
        {
            placeholder(R.drawable.ic_baseline_image_24)
            error(R.drawable.ic_baseline_error_24)
            scale(Scale.FILL)
        }

        binding.chipGroup.removeAllViews()

        item.chips.forEach {
            val chip = Chip(binding.firstChip.context)
            chip.text = it
            chip.textSize = 12F
            val typeFace = binding.firstChip.context?.let { it1 ->
                ResourcesCompat.getFont(
                    it1.applicationContext,
                    R.font.work_sans_medium
                )
            }

            chip.typeface = typeFace
            chip.layoutParams = binding.firstChip.layoutParams
            chip.chipEndPadding = 0F
            chip.chipStartPadding = 0F
            chip.setChipBackgroundColorResource(R.color.light_gray)

            binding.chipGroup.addView(chip)
        }
    }

    companion object {
        private val diffUtil: DiffUtil.ItemCallback<ThinMovie> =
            object : DiffUtil.ItemCallback<ThinMovie>() {
                override fun areItemsTheSame(oldItem: ThinMovie, newItem: ThinMovie): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: ThinMovie, newItem: ThinMovie): Boolean {
                    return oldItem == newItem
                }
            }
    }
}