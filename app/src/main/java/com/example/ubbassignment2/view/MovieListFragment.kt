package com.example.ubbassignment2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ubbassignment2.R
import com.example.ubbassignment2.adapter.MovieListAdapter
import com.example.ubbassignment2.databinding.FragmentListBinding
import com.example.ubbassignment2.model.ThinMovie
import com.example.ubbassignment2.viewmodel.MovieListViewModel

class MovieListFragment : Fragment(R.layout.fragment_list) {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val listAdapter = MovieListAdapter { item -> onItemClick(item) }

    private val viewModel: MovieListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.movieList.adapter = listAdapter
        binding.movieList.layoutManager = LinearLayoutManager(activity)

        binding.retryButton.setOnClickListener {
            viewModel.retry()
        }

        viewModel.movieList.observe(viewLifecycleOwner) {
            listAdapter.submitList(it)
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

    private fun onItemClick(item: ThinMovie) {
        findNavController().navigate(MovieListFragmentDirections.actionLandingToDetails(item.id))
    }


    private fun showErrorElements() {
        binding.retryButton.visibility = View.VISIBLE
        binding.messageTextView.visibility = View.VISIBLE
    }

    private fun hideErrorElements() {
        binding.retryButton.visibility = View.INVISIBLE
        binding.messageTextView.visibility = View.INVISIBLE
    }
}