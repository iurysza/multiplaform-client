package com.github.iurysza.common

import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter

actual fun CommonIcon(isoCode: String?): Painter {
  return runCatching {
    BitmapPainter(imageFromResource("ic_flag_$isoCode.jpg"))
  }.onFailure {
   return BitmapPainter(imageFromResource("ic_flag_al.jpg"))
  }.getOrNull() as Painter

//  return runCatching {
//    val imageVector = vectorXmlResource(isoCode!!)
//    return rememberVectorPainter(imageVector)
//  }.onFailure {
//    return BitmapPainter(imageFromResource("ic_flag_$isoCode.jpg"))
//  }.getOrElse {
//    BitmapPainter(imageFromResource("ic_flag_al.jpg"))
//  }
}
