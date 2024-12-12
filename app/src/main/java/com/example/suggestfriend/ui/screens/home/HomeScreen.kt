package com.example.suggestfriend.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.suggestfriend.ui.component.BodyText
import com.example.suggestfriend.ui.component.PrimaryButton
import com.example.suggestfriend.ui.theme.SuggestFriendTheme

@Composable
fun HomeScreen(navController: NavController) {
    var sliderValue by remember { mutableStateOf(5f) }
    ConstraintLayout(
        modifier = Modifier
            .scrollable(rememberScrollState(), Orientation.Vertical)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val (header, settings) = createRefs()
        Column(
            modifier = Modifier
                .constrainAs(header) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                }
                .height(IntrinsicSize.Max)
                .width(IntrinsicSize.Max)
        ) {
            BodyText(
                text = "Friends Suggestions",
                textColor = MaterialTheme.colorScheme.secondary,
                textStyle = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .constrainAs(settings) {
                    top.linkTo(header.bottom, margin = 32.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    width = Dimension.fillToConstraints
                }
                .height(IntrinsicSize.Max)
                .width(IntrinsicSize.Max)
        ) {
            BodyText(
                text = "Count down time for each suggestion frame: ${sliderValue.toInt()}",
                textColor = MaterialTheme.colorScheme.secondary,
                textStyle = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Slider(
                value = sliderValue,
                onValueChange = { newValue -> sliderValue = newValue },
                valueRange = 5f..20f
            )
            Spacer(modifier = Modifier.height(16.dp))
            PrimaryButton(name = "Show my suggestions!") {
                navController.navigate("suggestion_friend/${sliderValue}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SuggestFriendTheme {
        val navController = rememberNavController()
        HomeScreen(navController)
    }
}