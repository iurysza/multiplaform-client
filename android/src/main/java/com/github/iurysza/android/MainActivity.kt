package com.github.iurysza.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.github.iurysza.common.App
import com.github.iurysza.common.AppHomeViewModel
import com.github.iurysza.vaccinationtracker.VaccinationTracker
import com.github.iurysza.vaccinationtracker.cache.DatabaseDriverFactory

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      App(
        AppHomeViewModel(
          sdk = VaccinationTracker(DatabaseDriverFactory(this))
        )
      )
    }
  }
}