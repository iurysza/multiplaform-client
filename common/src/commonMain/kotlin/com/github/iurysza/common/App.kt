package com.github.iurysza.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.github.iurysza.common.ui.home.HomeScreen

@OptIn(
  ExperimentalFoundationApi::class, androidx.compose.ui.ExperimentalComposeUiApi::class,
  androidx.compose.material.ExperimentalMaterialApi::class
)
@Composable
fun App(homeViewModel: HomeViewModel) {
  MaterialTheme {
    HomeScreen(homeViewModel)
  }
}
