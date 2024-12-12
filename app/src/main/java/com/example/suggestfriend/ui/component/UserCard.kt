package com.example.suggestfriend.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.suggestfriend.R

@Composable
fun UserCard(
    username:String,
    name:String,
    age: Int,
    interests:List<String>,
    mutualFriends:Int,
    profilePictureUrl:String,
    modifier: Modifier =Modifier,
    sendInvite:()->Unit,
    ){
    val addFriendIcon = painterResource(id = R.drawable.ic_add_friend)
    Card(
        modifier = modifier
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.secondary,
            disabledContentColor = MaterialTheme.colorScheme.secondary,
            disabledContainerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            val interestBuilder = StringBuilder()
            val painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(profilePictureUrl)
                    .crossfade(true)
                    .placeholder(R.drawable.ic_downloading)
                    .error(R.drawable.ic_download_fail)
                    .build()
            )
            Column(
                modifier.padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row(
                    modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painter,
                        contentDescription = "User picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                    )
                    Column (
                        modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    ){
                        Row(
                            modifier.fillMaxWidth()
                        ) {
                            BodyText(
                                modifier.weight(0.7f),
                                text = name,
                                textColor = MaterialTheme.colorScheme.background,
                                textStyle = MaterialTheme.typography.bodyMedium
                            )
                            BodyText(
                                modifier.weight(0.3f),
                                text = "$age years",
                                textColor = MaterialTheme.colorScheme.background,
                                textStyle = MaterialTheme.typography.bodyMedium
                            )
                        }
                        BodyText(
                            text = username,
                            textColor = MaterialTheme.colorScheme.background,
                            textStyle = MaterialTheme.typography.bodyMedium
                        )
                        BodyText(
                            text = "$mutualFriends mutual friends",
                            textColor = MaterialTheme.colorScheme.background,
                            textStyle = MaterialTheme.typography.bodySmall
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    BodyText(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Interests",
                        textColor = MaterialTheme.colorScheme.background,
                        textStyle = MaterialTheme.typography.bodyMedium
                    )
                    for (interest in interests){
                        interestBuilder.append(interest)
                        interestBuilder.append(" ")
                    }
                    BodyText(
                        modifier = Modifier.fillMaxWidth(),
                        text = interestBuilder.toString(),
                        textColor = MaterialTheme.colorScheme.background,
                        textStyle = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    SecondaryButton(name = "Add", icon = addFriendIcon) {
                        sendInvite()
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun UserCardPreview(){
    UserCard(
        "username",
        "user",
        24,
        listOf("Travel"),
        3,
        ""
    ){

    }
}