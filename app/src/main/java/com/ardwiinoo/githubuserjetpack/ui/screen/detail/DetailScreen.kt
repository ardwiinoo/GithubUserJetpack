package com.ardwiinoo.githubuserjetpack.ui.screen.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImage
import com.ardwiinoo.githubuserjetpack.data.local.entity.Favorite
import com.ardwiinoo.githubuserjetpack.ui.theme.GithubUserJetpackTheme
import com.ardwiinoo.githubuserjetpack.utils.Result
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    username: String,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val userState by viewModel.userDetail.collectAsState()
    val isFavorite = remember { mutableStateOf(false) }

    LaunchedEffect(username) {
        viewModel.viewModelScope.launch {
            viewModel.getDetailUser(username)
            isFavorite.value = viewModel.isFavoriteUser(username)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when(userState) {
            is Result.Success -> {
                val user = (userState as Result.Success).data

                AsyncImage(
                    model = user.avatarUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = user.login,
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = user.followers.toString(),
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.padding(end = 8.dp)
                    )

                    Text(
                        text = "Followers",
                        style = MaterialTheme.typography.subtitle1
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = user.following.toString(),
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.padding(end = 8.dp)
                    )

                    Text(
                        text = "Following",
                        style = MaterialTheme.typography.subtitle1
                    )
                }
                Button(
                    onClick = {
                        if (isFavorite.value) {
                            viewModel.viewModelScope.launch {
                                viewModel.removeFromFavorites(user.login)
                            }
                            isFavorite.value = false
                        } else {
                            viewModel.viewModelScope.launch {
                                viewModel.addToFavorites(
                                    Favorite(
                                        avatarUrl = user.avatarUrl,
                                        username = user.login
                                    )
                                )
                            }
                            isFavorite.value = true
                        }
                    },
                ) {
                    Text(text = if (isFavorite.value) "Hapus dari Favorite" else "Tambah Favorite")
                }
            }
            is Result.Error -> {
                val error = (userState as Result.Error).exception
                Text(text = "Error: ${error?.message}")
            }
            is Result.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewBang() {
    GithubUserJetpackTheme() {
        DetailScreen(username = "budi")
    }
}