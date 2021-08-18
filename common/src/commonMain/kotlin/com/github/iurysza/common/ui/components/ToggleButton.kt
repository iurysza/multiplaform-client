package com.github.iurysza.common.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.iurysza.common.CommonIcon
import com.github.iurysza.vactrackerapp.ui.theme.ColorPrimary
import com.github.iurysza.vactrackerapp.ui.theme.ColorProgress

@Composable
fun ToggleButton(
  text: String,
  icon: Int = 0,
  checkedColor: Color = ColorPrimary,
  defaultColor: Color = ColorProgress,
  checked: Boolean, onChecked: (Boolean) -> Unit = {}
) {

  val backgroundTint by animateColorAsState(if (checked) defaultColor else Color(0xFFFFFFFF))
  val selectedColor by animateColorAsState(if (checked) checkedColor else defaultColor)

  Box(
    modifier = Modifier
      .clip(RoundedCornerShape(10.dp))
      .background(color = backgroundTint)
      .size(width = 74.dp, height = 60.dp)
  ) {
    IconToggleButton(
      checked = checked,
      onCheckedChange = onChecked,
      modifier = Modifier.fillMaxSize()
    ) {
      Column {
        Icon(
          painter = CommonIcon(),
          contentDescription = "Localized description",
          tint = selectedColor,
          modifier = Modifier.fillMaxWidth()
        )
        Text(
          text = text,
          color = selectedColor,
          modifier = Modifier.fillMaxWidth(),
          textAlign = TextAlign.Center,
        )
      }
    }
  }
}

@Composable
fun PreviewToggle() {
  ToggleButton(
    text = "Total",
    checked = true
  )
}
