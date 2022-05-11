package com.mina.dog.breed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.mina.dog.breed.databinding.BreedFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class BreedFragment : Fragment() {

    private val viewModel: BreedViewModel by viewModels()
    private val args: BreedFragmentArgs by navArgs()

    private lateinit var binding: BreedFragmentBinding

    @Inject
    internal lateinit var adapter: BreedGalleryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = BreedFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.initialize(args.breedName)
        observeViewState()


        binding.imageGallery.adapter = adapter
        binding.imageGallery.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }

    private fun observeViewState() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect {
                    binding.textView.visibility = VISIBLE
                    binding.viewGroup.visibility = GONE
                    when (it) {
                        is BreedViewModel.ViewState.Content -> showContent(it)
                        is BreedViewModel.ViewState.Loading -> {
                            binding.textView.text = getString(R.string.view_loading)
                        }
                        is BreedViewModel.ViewState.Error -> {
                            binding.textView.text = getString(R.string.view_error)
                        }
                    }
                }
            }
        }
    }

    private fun showContent(content: BreedViewModel.ViewState.Content) {
        binding.textView.visibility = GONE
        binding.viewGroup.visibility = VISIBLE

        (requireActivity() as AppCompatActivity)
            .supportActionBar
            ?.title = content.title

        with(binding) {
            subBreeds.text =
                if (content.subBreeds.isEmpty()) getString(R.string.sub_breeds_empty) else content.subBreeds

            adapter.updateItems(content.images)
        }
    }
}