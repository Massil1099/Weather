package fr.mastersid.massil.weather

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import fr.mastersid.massil.weather.data.Weather
import fr.mastersid.massil.weather.ui.theme.WeatherTheme
import fr.mastersid.massil.weather.viewmodel.WeatherListViewModel
import kotlin.collections.emptyList
import kotlin.collections.sortedBy


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherTheme {
                WeatherScreen (modifier = Modifier.fillMaxSize())
            }
        }
    }
}




@Composable
fun WeatherScreen(modifier: Modifier, weatherListViewModel: WeatherListViewModel = viewModel()) {
    var sortByCity: Boolean by rememberSaveable { mutableStateOf(false) }

    val weatherListNotSorted by weatherListViewModel.weatherList.observeAsState(emptyList())

    val refreshing: Boolean by weatherListViewModel.isUpdating.observeAsState(false)
    val weatherList = if (sortByCity) {
        weatherListNotSorted.sortedBy { weather -> weather.city }
    } else {
        weatherListNotSorted
    }


    Scaffold(
        modifier = Modifier,
        bottomBar = {
            SortByCitySwitch(
                modifier = Modifier.padding(horizontal = 16.dp),
                sortByCity = sortByCity
            ) { checked -> sortByCity = checked
            }
        },
                floatingActionButton = {
            FloatingActionButton(
                onClick = weatherListViewModel :: updateWeatherList
            ) {
                Icon(
                    painterResource (R. drawable.outline_circle_24) ,
                    stringResource (id = R.string.refresh_button_content_description)
                )
            }
        }
    ) { innerPadding ->
        Box ( modifier = Modifier . padding ( innerPadding ) . fillMaxSize () ) {

            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(bottom = WindowInsets.safeDrawing
                        .asPaddingValues()
                        .calculateBottomPadding()),

                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
            items(weatherList) { weather ->
                WeatherRow(weather)
            }
                item {
                    Spacer(modifier = Modifier.height(64.dp))
                }
        }
            if (refreshing) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }


    }



@Composable
fun WeatherRow( weather : Weather ) {
    Row( horizontalArrangement = Arrangement . spacedBy (16. dp) ) { // espace entre les ´el´e ments
        Text (
            text = weather .city ,
            maxLines = 1 , // pas de retour ˋa la ligne
            overflow = TextOverflow. Ellipsis , // pointill ´es finaux si le texte est trop long
            modifier = Modifier . weight (1f) // prend le maximum de place
        )
        Text ( text = stringResource (id = R.string.temperature , weather.temperature))
    }
}

@Composable
fun SortByCitySwitch ( modifier : Modifier , sortByCity : Boolean , onChange : ( Boolean ) -> Unit ) {
    Row(
        horizontalArrangement = Arrangement . spacedBy (16. dp) ,
        verticalAlignment = Alignment.CenterVertically ,
        modifier = modifier
    ) {
        Switch (
            checked = sortByCity ,
            onCheckedChange = onChange
        )
        Text ( stringResource (id = R. string . sort_by_city ) )
    }
}



@Composable
fun WeatherListScreen ( weatherListViewModel : WeatherListViewModel = viewModel () ) {

}






@Preview ( widthDp = 400)
@Composable
fun WeatherRowPreview () {
    WeatherTheme {
        WeatherRow(Weather (1 , "Saint - Etienne -du - Rouvray ", 11.0f) )
    }
}

@Preview ( widthDp = 400)
@Composable
fun SortByCitySwitchPreview () {
    WeatherTheme {
        SortByCitySwitch ( modifier = Modifier . padding ( horizontal = 16. dp) , sortByCity = true ) {
        }
    }
}




