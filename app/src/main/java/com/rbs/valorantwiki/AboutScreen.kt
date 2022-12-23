package com.rbs.valorantwiki

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.rbs.valorantwiki.ui.theme.ValorantWikiTheme

@Composable
fun AboutScreen(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = "About Me",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
        AsyncImage(
            model = "https://lh3.googleusercontent.com/drive-viewer/AFDK6gNo-p9Xit4xawpmm0mbUGowscbq9Yd0_8XOK8_05P33GyQ-FIFVf_REXWxhvIAV55m9fuAct7_tSkM7fz-3-zjrs42ySA=w1920-h865",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .padding(8.dp)
                .size(200.dp)
        )
        Text(
            text = "Robby Bagus Sadewa",
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "robbybaguss@gmail.com",
            fontStyle = FontStyle.Italic
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    ValorantWikiTheme {
        AboutScreen()
    }
}