package com.example.myapplication

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                SuccessScreen()
            }
        }
    }

    @Composable
    private fun SuccessScreen() {
        var isVisible by remember { mutableStateOf(true) }
        var isLoadingVisible by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                isVisible = true
                isLoadingVisible = false
            }) {
                Text(text = "Reset")
            }
            Box(contentAlignment = Alignment.Center) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AnimatedVisibility(
                        visible = isVisible,
                        enter = expandHorizontally(),
                        exit = shrinkHorizontally(
                            animationSpec = tween(
                                durationMillis = 2000,
                                delayMillis = 3000,
                                easing = { OvershootInterpolator().getInterpolation(it) }
                            )
                        )
                    ) {
                        Button(
                            onClick = {
                                isVisible = false
                                isLoadingVisible = true
                            }, modifier = Modifier
                                .fillMaxWidth()
                                .height(64.dp)
                                .border(
                                    1.dp,
                                    MaterialTheme.colorScheme.primary,
                                    CircleShape
                                ),
                            colors = ButtonDefaults.buttonColors(Color.Transparent)
                        ) {
                            Text(text = "Click me", color = MaterialTheme.colorScheme.primary)
                        }
                        ProgressDialog(isVisible = isLoadingVisible)
                    }
                }
                RoundedButton(isVisible = !isVisible)
            }
        }
    }

    @Composable
    private fun ProgressDialog(modifier: Modifier = Modifier, isVisible: Boolean) {
        if (isVisible) {
            Box(
                modifier = modifier
                    .padding(start = 286.dp, top = 11.dp),

                ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        }
    }

    @Composable
    private fun RoundedButton(modifier: Modifier = Modifier, isVisible: Boolean) {
        Box(modifier = modifier.padding(horizontal = 10.dp)) {
            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn(
                    animationSpec = tween(
                        durationMillis = 1000,
                        delayMillis = 3500
                    )
                ) + expandVertically(),
                exit = fadeOut() + shrinkVertically()

            ) {
                Button(
                    onClick = { },
                    shape = CircleShape,
                    modifier = modifier.size(56.dp),
                    contentPadding = PaddingValues(1.dp),
                    colors = ButtonDefaults.buttonColors(Color.Green)
                ) {
                    // Inner content including an icon and a text label
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        }
    }
}