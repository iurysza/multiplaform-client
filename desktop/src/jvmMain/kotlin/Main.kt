import androidx.compose.desktop.Window
import com.github.iurysza.common.App
import com.github.iurysza.common.AppHomeViewModel
import com.github.iurysza.vaccinationtracker.VaccinationTracker
import com.github.iurysza.vaccinationtracker.cache.DatabaseDriverFactory

fun main() = Window {
  App(
    AppHomeViewModel(
      sdk = VaccinationTracker(
        databaseDriverFactory = DatabaseDriverFactory()
      )
    )
  )
}