package com.example.lista5

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lista5.ui.theme.Lista5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lista5Theme() {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}



@Composable
fun E1(navController: NavHostController) {
    LazyColumn {
        items(20) { index ->
            val word = "List $index"
            Text(
                text = word,
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
                    .clickable { navController.navigate(Screens.E3Screen.route) }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun E1Preview() {
    Lista5Theme() {
        val navController = rememberNavController()
        E1(navController)
    }
}

@Composable
fun E3(){
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "First Screen",
            fontSize = 40.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun E3Preview() {
    Lista5Theme() {
        E3()
    }
}

@Composable
fun E2(){
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Green),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Second Screen",
            fontSize = 40.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun E2Preview() {
    Lista5Theme() {
        E2()
    }
}

sealed class Screens(val route: String) {
    data object E1Screen : Screens("e1")
    data object E3Screen : Screens("e3")
    data object E2Screen : Screens("e2")
}

sealed class BottomBar(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object E1 : BottomBar(Screens.E1Screen.route, "List of exercises", Icons.Default.Home)
    data object E2 : BottomBar(Screens.E2Screen.route, "Grades", Icons.Default.Email)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomMenu(navController = navController)},
        content = { BottomNavGraph(navController = navController) }
    )

}

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.E1Screen.route
    ) {
        composable(route = Screens.E1Screen.route) {
            E1(navController) // Pass navController to E1
        }
        composable(route = Screens.E2Screen.route) {
            E2()
        }
        composable(route = Screens.E3Screen.route) {
            E3() // Add E3 to the navigation graph
        }
    }
}

@Composable
fun BottomMenu(navController: NavHostController){
    val screens = listOf(
        BottomBar.E1, BottomBar.E2
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar{
        screens.forEach{screen ->
            NavigationBarItem(
                label = { Text(text = screen.title)},
                icon = {Icon(imageVector = screen.icon, contentDescription = "icon")},
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {navController.navigate(screen.route)}
            )
        }
    }
}
