package com.example.resqnow.Components

import android.R.attr.repeatMode
import android.content.Context
import android.net.Uri
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView


@Composable
fun VideoPlayerFromRaw(
    context: Context,
    rawResId: Int,
    modifier: Modifier = Modifier
) {
    val player = remember { ExoPlayer.Builder(context).build() }
    //lặp lạp video liên tục
    player.repeatMode = ExoPlayer.REPEAT_MODE_ALL
    // Mỗi lần rawResId đổi thì load lại video
    LaunchedEffect(rawResId) {
        val mediaItem = MediaItem.fromUri("android.resource://${context.packageName}/$rawResId")
        player.setMediaItem(mediaItem)
        player.prepare()
        player.playWhenReady = true

    }

    AndroidView(
        factory = {
            PlayerView(it).apply {
                useController = true
                this.player = player

            }
        },
        modifier = modifier
    )
}