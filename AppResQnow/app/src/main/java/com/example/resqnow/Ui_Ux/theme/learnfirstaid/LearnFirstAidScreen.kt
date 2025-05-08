package com.example.resqnow.Ui_Ux.theme.learnfirstaid

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.resqnow.R
import com.example.resqnow.Ui_Ux.theme.Router.Screen

// màn hình chức năng học sơ cứu

@Composable
fun FilterInstructor(navController: NavController,context: Context) {
    val instructors = remember {loadInstructorsFromAssets(context)}
//trạng thái
    var selectedLocation by remember { mutableStateOf<String?>(null) }
//lọc theo location
    val filteredInstructors = instructors.filter {
        selectedLocation == null || it.location == selectedLocation
    }
    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .navigationBarsPadding()
                    .fillMaxWidth()
                    .height(105.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.firstaid_navbar),
                    contentDescription = "Logo",
                    modifier = Modifier.fillMaxSize()
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { navController.navigate(Screen.HomePage1.route) },
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                            .size(60.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.home),
                                contentDescription = "Logo",
                                modifier = Modifier.size(41.dp, 39.dp)
                                    .clickable {
                                        navController.navigate(Screen.HomePage1.route)
                                    }
                            )
                        }
                    }
                    IconButton(
                        onClick = { navController.navigate(Screen.ContactScreen.route) },
                        modifier = Modifier
                            .padding(top = 40.dp)
                            .size(60.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.a),
                            contentDescription = "Logo",
                            alignment = Alignment.Center,
                            modifier = Modifier.size(37.dp, 32.dp)
                        )
                    }
                    IconButton(
                        onClick = {
                            if (navController.graph.findNode("Maps") != null) {
                                navController.navigate("Maps")
                            }
                        },
                        modifier = Modifier
                            .padding(top = 40.dp)
                            .size(60.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.hospital),
                            contentDescription = "Logo",
                            alignment = Alignment.Center,
                            modifier = Modifier.size(35.dp)
                        )
                    }
                    IconButton(
                        onClick = { navController.navigate(Screen.ProfileScreen.route) },
                        modifier = Modifier
                            .padding(top = 40.dp)
                            .size(60.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.c),
                            contentDescription = "Logo",
                            alignment = Alignment.Center,
                            modifier = Modifier.size(35.dp)
                        )
                    }
                }
            }
        }
    ) {paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues)
        ) {
// Bộ lọc vị trí hiện đại
            Text(
                text = "Chọn khu vực:",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFD32F2F)
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(modifier = Modifier.padding(bottom = 16.dp)) {
                val buttonModifier = Modifier
                    .padding(end = 8.dp)
                    .weight(1f)

                val selectedColor = Color.White
                val unselectedColor = Color(0xFFD32F2F)

                val selectedBackground = Color(0xFFD32F2F)
                val unselectedBackground = Color(0x1FD32F2F)

                //TPHCM
                Button(
                    onClick = { selectedLocation = "TP.HCM" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedLocation == "TP.HCM") selectedBackground else unselectedBackground,
                        contentColor = if (selectedLocation == "TP.HCM") selectedColor else unselectedColor
                    ),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(text = "TP.HCM", fontWeight = FontWeight.Medium)
                }

                //Hà Nội
                Button(
                    onClick = { selectedLocation = "Hà Nội" },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedLocation == "Hà Nội") selectedBackground else unselectedBackground,
                        contentColor = if (selectedLocation == "Hà Nội") selectedColor else unselectedColor
                    ),
                    shape = RoundedCornerShape(8.dp),

                    ) {
                    Text(text = "Hà Nội", fontWeight = FontWeight.Medium)

                }
                //Tất cả
                Button(
                    onClick = { selectedLocation = null },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedLocation == null) Color(0xFF1976D2) else Color(
                            0x1F1976D2
                        ),
                        contentColor = if (selectedLocation == null) selectedColor else Color(
                            0xFF1976D2
                        )
                    ),
                    shape = RoundedCornerShape(8.dp),
                )
                {
                    Text(text = "Tất cả", fontWeight = FontWeight.Medium)
                }

                Spacer(modifier = Modifier.width(25.dp))

                IconButton(onClick = { navController.navigate(Screen.HomePage1.route) }
                ) {
                    Image(painter = painterResource(id = R.drawable.exit), contentDescription = null,
                        alignment = Alignment.TopEnd,
                        modifier = Modifier
                            .size(24.dp)
                    )
                }
            }

            LazyColumn(modifier = Modifier.fillMaxSize().padding(top = 16.dp)) {
                items(filteredInstructors) { instructor ->
                    LearnFirstAidCard(instructor)
                }
            }
        }
    }

}
//Card
@Composable
fun LearnFirstAidCard(instructor: Instructor){
    val context = LocalContext.current
    Card (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFEBEE)
        ),
        elevation = CardDefaults.cardElevation(10.dp)
    ){
        Column(
            modifier = Modifier.padding(16.dp)
        ){
            Text(text = "${instructor.name}",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFD32F2F)
                )
            )
            Text(text = "${instructor.location}",
                modifier = Modifier.padding(top = 4.dp),
                color = Color.DarkGray
                )
            Text(text = "${instructor.description}",
                modifier = Modifier.padding(top = 4.dp),
                color = Color(0xFF37474F) // Xám xanh
            )
            Text(
                text = "📞 ${instructor.phone}",
                modifier = Modifier.padding(top = 4.dp),
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "✉️ ${instructor.email}",
                modifier = Modifier.padding(top = 4.dp),
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )
            Row(modifier = Modifier.padding(top = 8.dp)){
                repeat ((3..5).random()){
                    Image(painter = painterResource(id = R.drawable.baseline_star_rate_24),
                        contentDescription = "Star",
                    )
                }
            }
            Row(modifier = Modifier.padding(top = 3.dp)){
                Text(text ="~${instructor.price}",
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFB71C1C)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(text ="~${instructor.hours}",
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFB71C1C)
                )
            }
            // 2 Nút liên hệ
            Row(modifier = Modifier.padding(top = 12.dp)) {
                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_DIAL).apply {
                            data = Uri.parse("tel:${instructor.phone}")
                        }
                        context.startActivity(intent)
                    },
                    modifier = Modifier.padding(end = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD32F2F), // đỏ
                        contentColor = Color.White
                    )
                ) {
                    Text("Gọi", fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = {
                        val intent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:${instructor.email}")
                        }
                        context.startActivity(intent)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1976D2), // xanh dương
                        contentColor = Color.White
                    )
                ) {
                    Text("Email", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}