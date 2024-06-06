package com.example.task_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Background()
        }
    }
}

@Composable
fun NavigationBar(
    coins: MutableState<Int>, count: MutableState<Int>, progress: MutableState<Float>,
    kofMonet: MutableState<Int>, maxcount: MutableState<Int>,
    kofOpit: MutableState<Int>, level: MutableState<Int>
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        NavigationBarM3(coins, count, progress, kofMonet, maxcount, kofOpit, level)
    }
}

@Composable
fun NavigationBarM3(
    coins: MutableState<Int>, count: MutableState<Int>, progress: MutableState<Float>,
    kofMonet: MutableState<Int>, maxcount: MutableState<Int>,
    kofOpit: MutableState<Int>, level: MutableState<Int>
) {
    val kofAvtoMani = remember {
        mutableIntStateOf(0)
    }
    val kofAvtoOpit = remember {
        mutableIntStateOf(0)
    }
    var plusMani by remember {
        mutableIntStateOf(10)
    }
    var plusManiSec by remember {
        mutableIntStateOf(10)
    }
    var plusOpit by remember {
        mutableIntStateOf(10)
    }
    var plusOpitSec by remember {
        mutableIntStateOf(10)
    }
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        scope.launch {
            while (true) {
                coins.value += kofAvtoMani.intValue
                delay(1000)
            }
        }
    }
    LaunchedEffect(key1 = Unit) {
        scope.launch {
            while (true) {
                if (kofAvtoOpit.intValue != 0) {
                    if (count.value == maxcount.value) {
                        count.value = 0
                        level.value++
                        maxcount.value += 10
                        progress.value = 0f
                    }
                    count.value += kofAvtoOpit.intValue
                    val result: Float = (1.0f / maxcount.value) * kofAvtoOpit.intValue
                    progress.value += result
                    if (count.value > maxcount.value) {
                        count.value -= maxcount.value
                        level.value++
                        maxcount.value += 10
                        progress.value -= 1
                    }
                }
                delay(1000)
            }
        }
    }
    var selectedItem by remember { mutableIntStateOf(value = 0) }
    val barItem = listOf(
        "+1 манета $plusMani манет",
        "+1 опыт   $plusOpit манет",
        "+1 манета/сек $plusManiSec манет",
        "+1 опыт/сек $plusOpitSec манет",
    )

    NavigationBar(modifier = Modifier.fillMaxHeight(0.12f)) {
        barItem.forEachIndexed { index, barItem ->
            val selected = selectedItem == index
            NavigationBarItem(
                selected = selected,
                onClick = {
                    when (index) {
                        0 -> {
                            selectedItem = index
                            if (coins.value >= plusMani) {
                                coins.value -= plusMani
                                plusMani += 10
                                kofMonet.value++
                            }
                        }

                        1 -> {
                            selectedItem = index
                            if (coins.value >= plusOpit) {
                                coins.value -= plusOpit
                                plusOpit += 10
                                kofOpit.value++
                            }
                        }

                        2 -> {
                            selectedItem = index
                            if (coins.value >= plusManiSec) {
                                coins.value -= plusManiSec
                                plusManiSec += 10
                                kofAvtoMani.intValue++
                            }
                        }

                        3 -> {
                            selectedItem = index
                            if (coins.value >= plusOpitSec) {
                                coins.value -= plusOpitSec
                                plusOpitSec += 10
                                kofAvtoOpit.intValue++
                            }
                        }
                    }
                },
                icon = {},
                label = { Text(text = barItem, textAlign = TextAlign.Center) }
            )
        }
    }
}

@Composable
fun Background() {
    Image(
        painter = painterResource(id = R.drawable.fon),
        contentDescription = "image",
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        contentScale = ContentScale.Crop
    )
    Content()
}

@Composable
fun Content() {
    val kofMonet = remember { mutableIntStateOf(value = 1) }
    val kofOpit = remember { mutableIntStateOf(value = 1) }
    val counter = remember { mutableIntStateOf(value = 0) }
    val coins = remember { mutableIntStateOf(value = 0) }
    val count = remember { mutableIntStateOf(value = 0) }
    val maxcount = remember { mutableIntStateOf(value = 10) }
    val level = remember { mutableIntStateOf(value = 1) }
    val progress = remember { mutableFloatStateOf(value = 0.0f) }

    Column(
        modifier = Modifier
            .statusBarsPadding()
    ) {
        FirstElement(counter)
        NumberCoins(coins)
        ShowText(
            text = "Уровень ${level.intValue}",
            color = Color.Red, fontSize = 6f
        )

        LinearProgressIndicator(
            progress = progress.floatValue,
            modifier = Modifier
                .height(25.dp)
                .padding(top = 15.dp, start = 5.dp, end = 5.dp)
                .fillMaxWidth(),
            trackColor = Color.LightGray,
            color = Color.Green
        )
        ShowText(
            text = "Опыт ${count.intValue} из ${maxcount.intValue}",
            color = Color.White, fontSize = 6f
        )
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Red, shape = CircleShape)
                .clickable {
                    counter.intValue++
                    coins.intValue += kofMonet.intValue
                    if (count.intValue == maxcount.intValue) {
                        count.intValue = 0
                        level.intValue++
                        maxcount.intValue += 10
                        progress.floatValue = 0f
                    }
                    count.intValue += kofOpit.intValue
                    val result: Float = (1.0f / maxcount.intValue) * kofOpit.intValue
                    progress.floatValue += result
                    if (count.intValue > maxcount.intValue) {
                        count.intValue -= maxcount.intValue
                        level.intValue++
                        maxcount.intValue += 10
                        progress.floatValue -= 1
                    }
                },
            contentAlignment = Alignment.Center
        ) { ShowText("Нажми", Color.White, 4f) }
    }
    NavigationBar(coins, count, progress, kofMonet, maxcount, kofOpit, level)
}

@Composable
fun FirstElement(counter: MutableState<Int>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        ShowText("Вы кликнули ", Color.Green, 8f)
        ShowText(counter.value.toString(), Color.Green, 8f)
        ShowText(" раз", Color.Green, 8f)
    }
}

@Composable
fun NumberCoins(coins: MutableState<Int>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        ShowText("Монеты ", Color.Yellow, 8f)
        ShowText(coins.value.toString(), Color.Yellow, 8f)
        ShowText(" шт.", Color.Yellow, 8f)
    }
}

@Composable
fun ShowText(text: String, color: Color, fontSize: Float) {
    Text(
        text = text,
        color = color,
        fontSize = TextUnit(fontSize, TextUnitType.Em)
    )
}