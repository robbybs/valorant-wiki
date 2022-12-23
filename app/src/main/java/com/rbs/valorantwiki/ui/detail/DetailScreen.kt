package com.rbs.valorantwiki.ui.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.rbs.valorantwiki.config.UiState
import com.rbs.valorantwiki.config.ViewModelFactory
import com.rbs.valorantwiki.data.AgentRepository
import com.rbs.valorantwiki.model.Agent
import com.rbs.valorantwiki.ui.theme.ValorantWikiTheme

@Composable
fun DetailScreen(
    agentId: Long,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = ViewModelFactory(AgentRepository()))
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAgentById(agentId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.agent,
                    modifier
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    data: Agent,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = data.photoUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .padding(
                    start = 8.dp,
                    top = 16.dp,
                    end = 8.dp,
                    bottom = 8.dp
                )
                .size(200.dp)
        )
        Text(
            text = data.name,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = data.role,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = data.description,
            textAlign = TextAlign.Justify,
            modifier = modifier
                .padding(
                    start = 8.dp,
                    top = 16.dp,
                    end = 8.dp
                )
        )
    }

}

@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    ValorantWikiTheme {
        DetailContent(
            data = Agent(
                1,
                "Astra",
                "Ghanaian",
                "Controller",
                "xxx"
            )
        )
    }
}