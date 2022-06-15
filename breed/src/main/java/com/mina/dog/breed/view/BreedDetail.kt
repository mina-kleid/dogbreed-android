package com.mina.dog.breed.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import com.mina.dog.breed.BreedViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mina.dog.breed.R

@Composable
internal fun BreedDetail(viewModel: BreedViewModel = viewModel()) {
    val uiState = viewModel.viewState.collectAsState(initial = BreedViewModel.ViewState.Loading)

    when (val uiStateValue = uiState.value) {
        BreedViewModel.ViewState.Loading -> Loading()
        BreedViewModel.ViewState.Error -> Error()
        is BreedViewModel.ViewState.Content -> BreedDetailContent(uiStateValue)
    }
}

@Composable
internal fun Loading() {
    Text(text = stringResource(id = R.string.view_loading))
}

@Composable
internal fun Error() {
    Text(text = stringResource(id = R.string.view_error))
}

@Composable
internal fun BreedDetailContent(content: BreedViewModel.ViewState.Content) {
    BreedImageGallery(images = content.images)
    Text(text = content.subBreeds)
}