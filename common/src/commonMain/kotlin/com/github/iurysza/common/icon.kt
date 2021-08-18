package com.github.iurysza.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

@Composable
expect fun CommonIcon(isoCode: String?=null): Painter