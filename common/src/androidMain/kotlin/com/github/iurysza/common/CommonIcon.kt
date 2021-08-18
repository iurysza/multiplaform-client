package com.github.iurysza.common

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource

@Composable
actual fun CommonIcon(isoCode: String?): Painter {
  runCatching {
    return painterResource(LocalContext.current.getDrawableByName(isoCode!!))
  }.getOrElse {
    return painterResource(id = R.drawable.ic_flag_al)
  }
}

private fun Context.getDrawableByName(isoCode: String): Int {
  return resources.getIdentifier(
    "ic_flag_$isoCode",
    "drawable",
    packageName
  )
}
