package com.example.suggestfriend.ui.screens.suggestionfriend

import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.suggestfriend.R
import com.example.suggestfriend.ui.component.BodyText
import com.example.suggestfriend.ui.component.PrimaryButton
import com.example.suggestfriend.ui.component.UserCard

@Composable
fun SuggestionFriendScreen(countDownTime:Float,viewModel: SuggestionFriendViewModel = hiltViewModel()) {
    ConstraintLayout(
        modifier = Modifier
            .scrollable(rememberScrollState(), Orientation.Vertical)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        val threeUsers by viewModel.threeUsers.collectAsState()
        val (header, suggestionCards) = createRefs()
        val context = LocalContext.current
        val scale = remember { Animatable(0f) }
        val progress = remember { Animatable(1f) }
        var isFinished by remember {
            mutableStateOf(false)
        }
        val shuffleIcon = painterResource(id = R.drawable.ic_shuffle)
        val restartIcon = painterResource(id = R.drawable.ic_restart)
        LaunchedEffect(progress.value ==0f) {
            if(threeUsers.isNotEmpty()){
                viewModel.getThreeUsers()
            }
        }
        LaunchedEffect(Unit) {
            viewModel.initUsers()
        }
        LaunchedEffect(threeUsers) {
            if (threeUsers.isNotEmpty()) {
                isFinished = false
                scale.snapTo(0f)
                progress.snapTo(1f)
                progress.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(
                        durationMillis = countDownTime.toInt()*1000,
                        easing = LinearEasing
                    )
                )
            }else{
                isFinished=true
            }
        }
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
            if(!isFinished){
                LinearProgressIndicator(
                    progress = {progress.value},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .constrainAs(suggestionCards) {
                    top.linkTo(header.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                    content = {
                        items(threeUsers.size) { index ->
                            LaunchedEffect(threeUsers[index]) {
                                scale.animateTo(
                                    targetValue = 1f,
                                    animationSpec = spring(
                                        dampingRatio = Spring.DampingRatioLowBouncy,
                                        stiffness = Spring.StiffnessLow
                                    )
                                )
                            }
                            UserCard(
                                name = threeUsers[index].name,
                                username = threeUsers[index].username,
                                age = threeUsers[index].age,
                                interests = threeUsers[index].interests,
                                mutualFriends = threeUsers[index].mutualFriends,
                                profilePictureUrl = threeUsers[index].profilePicture,
                                modifier = Modifier.graphicsLayer(
                                    scaleX = scale.value,
                                    scaleY = scale.value
                                )
                            ) {
                                Toast.makeText(context, "Invite sent it", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                if (threeUsers.isNotEmpty()) {
                    PrimaryButton(name = "Shuffle", icon = shuffleIcon) {
                        viewModel.getThreeUsers()
                    }
                } else {
                    BodyText(
                        text = "You saw all suggestions",
                        textColor = MaterialTheme.colorScheme.secondary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    PrimaryButton(name = "Show again!", icon = restartIcon) {
                        viewModel.initUsers()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SuggestionFriendScreenPreview() {
    SuggestionFriendScreen(0f)
}