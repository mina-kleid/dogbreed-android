package com.mina.dog.breed.list

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mina.dog.breed.common.models.Breed
import com.mina.dog.breed.list.databinding.BreedListFragmentBinding
import com.mina.dog.breed.list.item.BreedListAdapter
import com.mina.dog.breed.list.item.BreedListItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
public class BreedListFragment : Fragment(), BreedListItemClickListener {

    private lateinit var binding: BreedListFragmentBinding
    private val viewModel: BreedListViewModel by viewModels()

    @Inject
    internal lateinit var adapter: BreedListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = BreedListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycle.addObserver(viewModel)
        observeViewStates()
        observeViewEvents()
        with(binding) {
            breedList.adapter = adapter
            breedList.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observeViewEvents() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewEvent.collect {
                    when (it) {
                        is BreedListViewModel.ViewEvent.Navigate -> {
                            val uri = Uri.parse(it.uriString)
                            findNavController().navigate(uri)
                        }
                    }
                }
            }
        }
    }

    private fun observeViewStates() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect {
                    binding.textView.visibility = View.VISIBLE
                    when (it) {
                        is BreedListViewModel.ViewState.Content -> {
                            binding.textView.visibility = View.GONE
                            adapter.updateAdapter(it.breeds)
                        }
                        BreedListViewModel.ViewState.Loading ->
                            binding.textView.text = getString(R.string.list_loading)
                        BreedListViewModel.ViewState.Error ->
                            binding.textView.text = getString(R.string.list_error)
                    }
                }
            }
        }
    }

    override fun onItemClicked(breed: Breed) {
        viewModel.breedClicked(breed)
    }

}