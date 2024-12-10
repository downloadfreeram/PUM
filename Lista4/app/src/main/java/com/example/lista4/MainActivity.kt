package com.example.Lista4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    private val questions = listOf(
        Question(
            text = "What is the capital of France?",
            answers = listOf("Berlin", "Madrid", "Paris", "Rome"),
            correctAnswer = "Paris"
        ),
        Question(
            text = "Which planet is known as the Red Planet?",
            answers = listOf("Venus", "Mars", "Jupiter", "Saturn"),
            correctAnswer = "Mars"
        ),
        Question(
            text = "How many continents are there on Earth?",
            answers = listOf("5", "6", "7", "8"),
            correctAnswer = "7"
        ),
        Question(
            text = "What is the boiling point of water at sea level?",
            answers = listOf("90°C", "100°C", "110°C", "120°C"),
            correctAnswer = "100°C"
        ),
        Question(
            text = "What gas do plants absorb from the atmosphere?",
            answers = listOf("Oxygen", "Carbon Dioxide", "Nitrogen", "Helium"),
            correctAnswer = "Carbon Dioxide"
        ),
        Question(
            text = "Which element has the atomic number 1?",
            answers = listOf("Oxygen", "Helium", "Hydrogen", "Carbon"),
            correctAnswer = "Hydrogen"
        ),
        Question(
            text = "Who wrote 'Romeo and Juliet'?",
            answers = listOf("Charles Dickens", "Mark Twain", "William Shakespeare", "Jane Austen"),
            correctAnswer = "William Shakespeare"
        ),
        Question(
            text = "What is the largest mammal?",
            answers = listOf("Elephant", "Blue Whale", "Giraffe", "Great White Shark"),
            correctAnswer = "Blue Whale"
        ),
        Question(
            text = "How many colors are in a rainbow?",
            answers = listOf("5", "6", "7", "8"),
            correctAnswer = "7"
        ),
        Question(
            text = "Which ocean is the largest?",
            answers = listOf("Atlantic", "Indian", "Arctic", "Pacific"),
            correctAnswer = "Pacific"
        )
    ).shuffled()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizApp(questions)
        }
    }
}

data class Question(val text: String, val answers: List<String>, val correctAnswer: String)

@Composable
fun QuizApp(questions: List<Question>) {
    var questionIndex by remember { mutableIntStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var progress by remember { mutableFloatStateOf(0f) }
    val currentQuestion = questions[questionIndex]

    if (questionIndex < questions.size) {
        QuizScreen(
            question = currentQuestion,
            questionNumber = questionIndex + 1,
            totalQuestions = questions.size,
            score = score,
            progress = progress,
            onNext = { selectedAnswer ->
                if (selectedAnswer == currentQuestion.correctAnswer) {
                    score++
                }
                progress = (questionIndex + 1) / questions.size.toFloat()
                if (questionIndex + 1 < questions.size) {
                    questionIndex++
                } else {
                    progress = 1f
                }
            }
        )
    } else {
        ResultScreen(score = score, totalQuestions = questions.size)
    }
}

@Composable
fun QuizScreen(
    question: Question,
    questionNumber: Int,
    totalQuestions: Int,
    score: Int,
    progress: Float,
    onNext: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Question $questionNumber/$totalQuestions",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .padding(vertical = 8.dp),
        )
        Text(
            text = question.text,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
        )
        question.answers.forEach { answer ->
            Button(
                onClick = { onNext(answer) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(text = answer, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun ResultScreen(score: Int, totalQuestions: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Congratulations!",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Green
        )
        Text(
            text = "Your score is: $score/$totalQuestions",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(vertical = 16.dp)
        )
    }
}
