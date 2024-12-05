package edu.lawrence.dining

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.lawrence.dining.ui.theme.DiningTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val vm = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(ReviewsModel::class.java)
        setContent {
            DiningTheme {
                DiningApp(vm,modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(vm : ReviewsModel, toCreate : ()->Unit,modifier: Modifier) {
    val reviews = remember { mutableStateOf(vm.reviews) }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    "Dining Ratings",
                )
            },
            actions = {
                IconButton(onClick = { toCreate() }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Review"
                    )
                }
            },
        )
    },
        modifier = Modifier.fillMaxSize())
    { innerPadding ->
        Column(verticalArrangement = Arrangement.Top, modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            reviews.value.forEach { review ->
                key(review.food) { ReviewItem(review = review)
                    }
            }
        }
    }
}


@Composable
fun DiningApp(vm : ReviewsModel,modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(navController,startDestination="create") {
        composable(route="list") {
            ListScreen(vm = vm,
                toCreate = { navController.navigate("create") },
                modifier = modifier)
        }
        composable(route="create") {
            CreateScreen(vm = vm,toList = { navController.navigate("list")}, modifier = modifier)
        }
    }
}

@Composable
fun CreateScreen(vm : ReviewsModel, toList : () -> Unit, modifier : Modifier) {
    val focusManager = LocalFocusManager.current
    val food = remember { mutableStateOf("") }
    val rating = remember { mutableStateOf("") }

    fun makeNewReview() {
        vm.createReview(food.value, rating.value.toInt())
        toList()
    }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize().padding(10.dp)
    ) {
        Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.Bottom) {
            Text("Food:")
            Spacer(modifier = Modifier.width(10.dp))
            TextField(
                value = food.value,
                onValueChange = { newValue: String -> food.value = newValue },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() })
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.Bottom) {
            Text("Rating:")
            Spacer(modifier = Modifier.width(10.dp))
            TextField(
                value = rating.value,
                onValueChange = { newValue: String -> rating.value = newValue },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() })
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { makeNewReview() }) {
            Text("Save")
        }
    }
}

@Composable
fun ReviewItem(review: Review) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Text(review.food)
        Spacer(modifier = Modifier.width(20.dp))
        Text(review.rating.toString())
        Spacer(modifier = Modifier.width(20.dp))
        Text(review.date)
    }
}