package edu.lawrence.finalexam

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.lawrence.finalexam.ui.theme.FinalExamTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinalExamTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GuideApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun GuideApp(modifier: Modifier){
    val vm : GuideModel = viewModel()
    val navController = rememberNavController()
    NavHost(navController,startDestination="list") {
        composable(route = "list") {
            ListView(vm, toCreate = { navController.navigate("create")}, toGuide = { navController.navigate("guide")} , modifier = modifier)
        }
        composable(route = "create") {
            CreateView(vm, toGuide = { navController.navigate("guide") }, toList = { navController.navigate("list") },modifier = modifier)
        }
        composable(route = "guide") {
            GuideView(vm, toList = { navController.navigate("list") }, modifier = modifier)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuideView(vm: GuideModel, toList: () -> Unit, modifier: Modifier) {
    val adding = remember { mutableStateOf(false) }
    val steps = remember {vm.steps}
    val guide = remember {vm.selectedGuide}
    Scaffold(topBar = {
        TopAppBar(title = {
            guide.value?.let { Text(it.name) }
        })
    },
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = toList) {
                        Icon(
                            imageVector = Icons.Default.List,
                            contentDescription = "View Lists"
                        )
                    }
                    IconButton(onClick = {
                        adding.value = true
                    }){
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Step"
                        )
                    }

                }
            )
        }
    ){
            innerPadding ->
        Column(verticalArrangement = Arrangement.Top, modifier = Modifier.fillMaxSize().padding(innerPadding)){
            steps.value?.forEach{ step ->
                Text(step.heading)
                Text(step.narration)
                Spacer(modifier = Modifier.height(10.dp))
            }
            if(adding.value){
                    addStep(vm, modifier)
                    Button(onClick = {
                        adding.value = false
                    }) { Text("Done") }
            }else{
                Button(onClick = {
                    toList()
                }){
                    Text("Go Back")
                }
            }
        }
    }
}

@Composable
fun addStep(vm: GuideModel, modifier: Modifier){
    val header = remember { mutableStateOf("") }
    val narration = remember { mutableStateOf("")}
    val guide = remember{vm.selectedGuide}
    val focusManager = LocalFocusManager.current

    Row(
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Header:")
        Spacer(modifier = Modifier.width(10.dp))
        TextField(value = header.value,
            onValueChange = { newValue: String -> header.value = newValue },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
    Row(
        horizontalArrangement = Arrangement.Start,

        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Narration:")
        Spacer(modifier = Modifier.width(10.dp))
        TextField(value = narration.value,
            onValueChange = { newValue: String -> narration.value = newValue },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
        )
    }
    Spacer(modifier =Modifier.height(10.dp))
    Button(onClick = {
        val newStep = guide.value?.let {
            Step(
                stepNumber = (vm.steps.value?.size ?: 0) + 1,
                heading = header.value,
                narration = narration.value,
                guide = it.idGuide
            )
        }
        if (newStep != null) {
            vm.addStep(newStep)
        }
    }) { Text("Save") }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateView(vm: GuideModel, toGuide: () -> Unit, toList: () -> Unit, modifier: Modifier) {
    val name = remember{ mutableStateOf("")}
    val focusManager = LocalFocusManager.current
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("Guide App")
        })
    },
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = toList) {
                        Icon(
                            imageVector = Icons.Default.List,
                            contentDescription = "View Lists"
                        )
                    }
                }
            )
        }
    ){
            innerPadding ->
        Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize().padding(innerPadding)){
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("New Guide Name:")
                Spacer(modifier = Modifier.width(10.dp))
                TextField(value = name.value,
                    onValueChange = { newValue: String -> name.value = newValue },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() })
                )
            }
            Spacer(Modifier.height(20.dp))
            Button(onClick = {
                val guide = Guide(name = name.value)
                vm.addGuide(guide)
                toGuide()
            }) {
                Text("Add Guide")
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListView(vm : GuideModel, toCreate : () -> Unit, toGuide : () -> Unit, modifier: Modifier){
    val guides = remember{ vm.guides}
    Scaffold(topBar = {
        TopAppBar(title = {
            Text("Guide App")
        })
                      },
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = toCreate) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add a Guide"
                        )
                    }
                }
            )
        }
    ){
        innerPadding ->
        Column(verticalArrangement = Arrangement.Top, modifier = Modifier.fillMaxSize().padding(innerPadding)){
            guides.value?.forEach { guide ->
                TextButton(onClick = {
                    vm.getSteps(guide.idGuide)
                    toGuide()
                }) {
                    Text(guide.name)
                }

            }
        }
    }
}




