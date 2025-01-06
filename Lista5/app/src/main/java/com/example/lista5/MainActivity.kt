package com.example.lista5

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lista5.ui.theme.E1ViewModel
import com.example.lista5.ui.theme.Lista5Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lista5Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val e1ViewModel: E1ViewModel = viewModel()
    Scaffold(
        bottomBar = { BottomMenu(navController) },
        content = { BottomNavGraph(navController, e1ViewModel) }
    )
}

@Composable
fun BottomNavGraph(navController: NavHostController, e1ViewModel: E1ViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screens.E1Screen.route
    ) {
        composable(Screens.E1Screen.route) {
            E1Screen(navController, e1ViewModel)
        }
        composable(Screens.E2Screen.route) {
            E2Screen(navController, e1ViewModel)
        }
        composable(Screens.E3Screen.route) { backStackEntry ->
            val numOfExercises = backStackEntry.arguments?.getString("numOfExercises")?.toInt() ?: 0
            E3Screen(numOfExercises)
        }
    }
}

sealed class Screens(val route: String) {
    data object E1Screen : Screens("e1")
    data object E3Screen : Screens("e3/{numOfExercises}") {
        fun createRoute(numOfExercises: Int):String = "e3/$numOfExercises"
    }
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

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun E1Screen(
    navController: NavHostController,
    viewModel: E1ViewModel
) {
    val taskLists by viewModel.getTaskLists().observeAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Task List", style = MaterialTheme.typography.titleLarge) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        },
        content = { paddingValues -> // Use paddingValues provided by Scaffold
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues) // Apply padding here
            ) {
                if (taskLists.isEmpty()) {
                    Text(
                        text = "No tasks available.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(taskLists) { task ->
                            TaskCard(
                                title = "${task.subject}: ${task.taskName}",
                                description = "Number of Exercises: ${task.numOfExercises} (Grade: ${task.grade})",
                                points = task.numOfExercises,
                                onClick = {
                                    navController.navigate(Screens.E3Screen.createRoute(task.numOfExercises))
                                }
                            )
                        }
                    }
                }
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun E2Screen(
    navController: NavHostController,
    e1ViewModel: E1ViewModel
) {
    val subjectSummary = e1ViewModel.getSubjectSummary()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Subject Summary", style = MaterialTheme.typography.titleLarge) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        },
        content = { paddingValues -> // Use paddingValues provided by Scaffold
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues) // Apply padding here
            ) {
                if (subjectSummary.isEmpty()) {
                    Text(
                        text = "No subjects available.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(subjectSummary.keys.toList()) { subject ->
                            val (totalLists, averageGrade) = subjectSummary[subject] ?: Pair(0, 0.0)
                            SubjectSummaryCard(
                                subject = subject.toString(),
                                averageGrade = averageGrade,
                                totalLists = totalLists
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun E3Screen(numOfExercises: Int) {
    val exampleTasks = (1..numOfExercises).map { index ->
        "Task $index" to "Lorem Ipsum..."
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(exampleTasks) { (title, description) ->
            TaskCard(title = title, description = description, points = 1, onClick = {})
        }
    }
}

@Composable
fun TaskCard(title: String, description: String, points: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.medium)
            .clickable(onClick = onClick)
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "Points: $points",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
@Composable
fun SubjectSummaryCard(subject: String, averageGrade: Double, totalLists: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.medium)
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Column {
            Text(
                text = subject,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Average Grade: ${"%.2f".format(averageGrade)}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "Total Lists: $totalLists",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}


@Composable
fun BottomMenu(navController: NavHostController) {
    val screens = listOf(
        BottomBar.E1,
        BottomBar.E2
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        screens.forEach { screen ->
            NavigationBarItem(
                label = { Text(text = screen.title) },
                icon = { Icon(imageVector = screen.icon, contentDescription = null) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = { navController.navigate(screen.route) }
            )
        }
    }
}
