package com.rbs.valorantwiki.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.rbs.valorantwiki.config.UiState
import com.rbs.valorantwiki.ValorantApp
import com.rbs.valorantwiki.config.ViewModelFactory
import com.rbs.valorantwiki.data.AgentRepository
import com.rbs.valorantwiki.model.OrderAgent
import com.rbs.valorantwiki.ui.theme.ValorantWikiTheme

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(AgentRepository())),
    navigateToDetail: (Long) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllAgents()
            }
            is UiState.Success -> {
                HomeContent(
                    orderAgent = uiState.data,
                    modifier = modifier,
                    navigateToDetail
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    orderAgent: List<OrderAgent>,
    modifier: Modifier,
    navigateToDetail: (Long) -> Unit
) {
    Box(modifier = modifier) {
        val listState = rememberLazyListState()
        LazyColumn(
            state = listState
        ) {
            items(orderAgent) { data ->
                AgentListItem(
                    id = data.agent.id,
                    name = data.agent.name,
                    role = data.agent.role,
                    photoUrl = data.agent.photoUrl,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail
                )
            }
        }
    }
}

@Composable
fun AgentListItem(
    id: Long,
    name: String,
    role: String,
    photoUrl: String,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit
) {
    Card(Modifier.padding(8.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .clickable {
                    navigateToDetail(id)
                },
        ) {
            AsyncImage(
                model = photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(8.dp)
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Column(modifier = modifier) {
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp)
                )
                Text(
                    text = role,
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp)
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ValorantWikiTheme {
        ValorantApp()
    }
}