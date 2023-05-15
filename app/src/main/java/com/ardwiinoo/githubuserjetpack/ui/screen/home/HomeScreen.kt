package com.ardwiinoo.githubuserjetpack.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImage
import com.ardwiinoo.githubuserjetpack.data.remote.response.ItemsItem
import com.ardwiinoo.githubuserjetpack.ui.theme.GithubUserJetpackTheme
import com.ardwiinoo.githubuserjetpack.utils.Result
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    var username by remember { mutableStateOf("") }
    val searchResult by viewModel.userList.collectAsState()

    Column(modifier = modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = username,
                onValueChange = { newValue ->
                    if(newValue.isNotEmpty() && newValue != "") {
                        username = newValue
                    }
                },
                label = { Text(text = "Search For Users") },
                modifier = Modifier
                    .weight(1f)
                    .height(IntrinsicSize.Min)
            )
            Button(
                onClick = {
                    viewModel.viewModelScope.launch {
                        viewModel.searchUsers(username)
                    }
                },
                modifier = Modifier
                    .padding(4.dp)
                    .height(IntrinsicSize.Min)
            ) {
                Text(text = "Search")
            }
        }

        when(searchResult) {
            is Result.Success -> {
                val users = (searchResult as Result.Success).data.items
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(users) { user ->
                        UserCard(user = user) {

                        }
                    }
                }
            }
            is Result.Error -> {
                val error = (searchResult as Result.Error).exception
                Text(text = "Error: ${error?.message}")
            }
            is Result.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
        }
    }
}

@Composable
fun UserCard(user: ItemsItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = user.avatarUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = user.login,
                style = MaterialTheme.typography.h5
            )
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