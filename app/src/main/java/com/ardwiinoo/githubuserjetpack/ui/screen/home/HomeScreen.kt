package com.ardwiinoo.githubuserjetpack.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ardwiinoo.githubuserjetpack.ui.theme.GithubUserJetpackTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    var username by remember { mutableStateOf("") }
//    val searchResult by viewModel.userList.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(text = "Search For Users") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {},
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Search")
        }
    }
}

@Preview(showBackground = true)
@Composable()
fun HomeScreenPreview() {
    GithubUserJetpackTheme {
        HomeScreen()
    }
}