package com.uvg.ana.booktribev2.userprofile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uvg.ana.booktribev2.R



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class UserProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserProfileNavigation()
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(
    onEditProfileClick: () -> Unit, // Callback para el botón de editar perfil
    onSettingsClick: () -> Unit,    // Callback para el botón de configuraciones
) {
    // Utilizando Material3 Theme con Dark Mode
    MaterialTheme(
        colorScheme = darkColorScheme() // Esquema de colores oscuros
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background // Fondo oscuro
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Imagen de fondo (banner)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.booktribelogodark), // Reemplaza con tu imagen de fondo
                        contentDescription = "Background image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                // Imagen de perfil circular parcialmente superpuesta
                Box(
                    modifier = Modifier
                        .offset(y = (-50).dp) // Mover hacia arriba para superponer sobre el fondo
                        .size(120.dp)
                        .clip(CircleShape)
                        .shadow(8.dp, CircleShape)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.booktribelogodark), // Reemplaza con tu imagen de perfil
                        contentDescription = "Profile Picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(16.dp)) // Espacio entre la imagen y el texto

                // Nombre del usuario
                Text(
                    text = "Samantha Josephus", // Puedes reemplazar con datos reales
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp)
                )

                // Nombre de usuario
                Text(
                    text = "@sam_jose3", // Puedes reemplazar con datos reales
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Biografía del usuario
                Text(
                    text = "Una entusiasta de la lectura de ceincia ficcion y aventura. Dispuesta a descubrir nuevos libros para abrir mi mente",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp),
                    lineHeight = 20.sp
                )

                // Botones debajo de la biografía
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = onEditProfileClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Edit Profile")
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = onSettingsClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Settings")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserProfileScreen() {
    UserProfileScreen(
        onEditProfileClick = {},
        onSettingsClick = {}
    )
}
