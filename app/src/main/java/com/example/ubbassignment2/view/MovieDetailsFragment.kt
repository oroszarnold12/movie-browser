package com.example.ubbassignment2.view

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.ubbassignment2.R
import com.example.ubbassignment2.databinding.FragmentDetailsBinding
import com.example.ubbassignment2.model.Movie
import com.example.ubbassignment2.viewmodel.MovieDetailsViewModel
import com.google.android.material.chip.Chip

class MovieDetailsFragment : Fragment(R.layout.fragment_details) {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: MovieDetailsFragmentArgs by navArgs()

    private val viewModel: MovieDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.getMovie(args.movieId)

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.retryButton.setOnClickListener {
            viewModel.retry(args.movieId)
        }

        viewModel.movie.observe(viewLifecycleOwner) {
            onMovieArrived(movie = it)
            hideErrorElements()
        }

        viewModel.isError.observe(viewLifecycleOwner) {
            if (it) {
                showErrorElements()
                binding.progressBar.visibility = View.INVISIBLE
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) binding.progressBar.visibility = View.VISIBLE
        }

        viewModel.isNormal.observe(viewLifecycleOwner) {
            if (it) binding.progressBar.visibility = View.INVISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onMovieArrived(movie: Movie) {
        binding.imageView.load(
            movie.imageUrl
        )
        {
            placeholder(R.drawable.ic_baseline_image_24)
            error(R.drawable.ic_baseline_error_24)
        }

        // setting the title
        binding.movieTitle.text = movie.title

        // setting the characteristics
        val del = SpannableString("  |  ")
        del.setSpan(
            ForegroundColorSpan(Color.parseColor("#000000")),
            0,
            del.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        val characteristics = movie.year + del + movie.pg + del + movie.time + del + movie.lang
        binding.characteristics.text = characteristics

        // setting the description
        binding.movieDescription.text = movie.description

        // adding the chips
        movie.chips.forEach {
            val chip = Chip(activity)
            chip.text = it
            chip.textSize = 12F
            val typeFace = activity?.let { it1 ->
                ResourcesCompat.getFont(
                    it1.application,
                    R.font.work_sans_medium
                )
            }

            chip.typeface = typeFace
            chip.chipEndPadding = 0F
            chip.chipStartPadding = 0F
            chip.setChipBackgroundColorResource(R.color.light_gray)

            binding.chipGroup.addView(chip)
        }

        // setting the tomatometer percentage
        binding.tomatoMeter.text = movie.tomatoPercentage

        // setting the rating bar value
        binding.ratingBar.rating = movie.rating.toFloat()

        // setting the rating bar text
        val rating = SpannableStringBuilder(movie.rating.toString())
        val numberOfRatings = SpannableStringBuilder(movie.numOfRating)

        rating.setSpan(
            AbsoluteSizeSpan(binding.rateIt.textSize.toInt()),
            0,
            rating.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        rating.append("/10")
        rating.setSpan(
            ForegroundColorSpan(Color.parseColor("#212031")),
            0,
            rating.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        numberOfRatings.setSpan(
            ForegroundColorSpan(Color.parseColor("#73212031")),
            0,
            numberOfRatings.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        rating.append("\n", numberOfRatings)
        binding.ratingBarText.text = rating
    }

    private fun showErrorElements() {
        binding.retryButton.visibility = View.VISIBLE
        binding.messageTextView.visibility = View.VISIBLE

        binding.blueLine.visibility = View.INVISIBLE
        binding.grayLine.visibility = View.INVISIBLE
        binding.ratingBar.visibility = View.INVISIBLE
        binding.rateIt.visibility = View.INVISIBLE
        binding.tomatoMeter.visibility = View.INVISIBLE
        binding.tomatoMeterName.visibility = View.INVISIBLE
    }

    private fun hideErrorElements() {
        binding.retryButton.visibility = View.INVISIBLE
        binding.messageTextView.visibility = View.INVISIBLE

        binding.blueLine.visibility = View.VISIBLE
        binding.grayLine.visibility = View.VISIBLE
        binding.ratingBar.visibility = View.VISIBLE
        binding.rateIt.visibility = View.VISIBLE
        binding.tomatoMeter.visibility = View.VISIBLE
        binding.tomatoMeterName.visibility = View.VISIBLE
    }
}