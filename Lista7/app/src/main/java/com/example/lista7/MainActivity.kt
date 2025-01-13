package com.example.lista7

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lista7.ui.theme.Lista7Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lista7Theme {
                AppNavigation()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val studentViewModel: StudentViewModel = viewModel()
    NavHost(navController = navController, startDestination = "MainScreen") {
        composable("MainScreen") {
            MainScreen(navController, studentViewModel)
        }
        composable(
            route = "DetailScreen/{indexNumber}",
            arguments = listOf(
                navArgument("indexNumber") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val indexNumber = backStackEntry.arguments?.getInt("indexNumber")
            DetailScreen(indexNumber, studentViewModel)
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController, viewModel: StudentViewModel) {
    val students by viewModel.students.observeAsState(emptyList())

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Student List",
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        students.forEach { student ->
            Text(
                text = "Index: ${student.indexNumber}, ${student.name} ${student.surname}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        navController.navigate("DetailScreen/${student.indexNumber}")
                    }
            )
        }
    }
}

@Composable
fun DetailScreen(indexNumber: Int?, viewModel: StudentViewModel) {
    val student = indexNumber?.let { viewModel.getStudentByIndex(it) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (student != null) {
                Text(
                    text = "Student Details",
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(text = "Index: ${student.indexNumber}")
                Text(text = "Name: ${student.name} ${student.surname}")
                Text(text = "Average Grades: ${student.avgGrades}")
                Text(text = "Year: ${student.year}")
            } else {
                Text(text = "Student not found.")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppNavigation()
}