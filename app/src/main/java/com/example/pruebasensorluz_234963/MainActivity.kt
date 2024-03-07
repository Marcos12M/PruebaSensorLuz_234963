package com.example.pruebasensorluz_234963

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import java.lang.reflect.Modifier
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContent{
            val viewModel = viewModel<MainViewModel>()
            val isDark = viewModel.isDark
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        if(isDark) Color.DKGRAY else Color.WHITE
                    ),
                contentAlignment = Layout.Alignment.ALIGN_CENTER
            ) {
                Text(
                    text = if(isDark){
                        "Esta oscuro afuera"
                    } else {
                        "NO esta oscuro afuera"
                    },
                    text = if(isDark) Color.WHITE else Color.DKGRAY
                )
            }
        }
    }
}