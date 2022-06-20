package com.mina.dog.breed.view

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mina.dog.breed.BreedViewModel
import com.mina.dog.breed.R

@Composable
internal fun BreedDetail(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    viewModel: BreedViewModel = viewModel()
) {
    val uiState = viewModel.viewState.collectAsState(initial = BreedViewModel.ViewState.Error)

    Column(
        modifier
            .fillMaxWidth()
            .verticalScroll(state = scrollState)
    ) {
        when (val uiStateValue = uiState.value) {
            BreedViewModel.ViewState.Loading -> Loading()
            BreedViewModel.ViewState.Error -> Error()
            is BreedViewModel.ViewState.Content -> BreedDetailContent(uiStateValue)
        }
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
    Text(text = stringResource(id = R.string.sub_breeds_header))
    if (content.subBreeds != null) {
        Text(text = content.subBreeds)
    } else {
        Text(text = stringResource(id = R.string.sub_breeds_empty))
    }
}