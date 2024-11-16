package com.uvg.ana.booktribev2.userprofile

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uvg.ana.booktribev2.R

import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditUserProfileScreen(
    onSaveClick: () -> Unit,    // Callback para el botón de guardar
    onCancelClick: () -> Unit   // Callback para el botón de cancelar
) {
    var name by remember { mutableStateOf("Jorge Dayanna") }
    var bio by remember { mutableStateOf("Lector ávido, desarrollador de software.") }
    var age by remember { mutableStateOf("") }
    var isPrivate by remember { mutableStateOf(false) }

    // Para el selector de fecha
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
            age = "$selectedDay/${selectedMonth + 1}/$selectedYear"
        }, year, month, day
    )

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
                // Imagen de fondo (banner) editable
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                        .clickable { /* Acción para editar foto de fondo */ }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.booktribelogodark), // Reemplaza con tu imagen de fondo
                        contentDescription = "Background image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Text(
                        text = "Edit background",
                        color = Color.White,
                        modifier = Modifier.align(Alignment.BottomCenter).padding(8.dp)
                    )
                }

                // Imagen de perfil circular editable
                Box(
                    modifier = Modifier
                        .offset(y = (-50).dp) // Mover hacia arriba para superponer sobre el fondo
                        .size(120.dp)
                        .clip(CircleShape)
                        .clickable { /* Acción para editar foto de perfil */ }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.booktribelogodark), // Reemplaza con tu imagen de perfil
                        contentDescription = "Profile Picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Text(
                        text = "Edit profile picture",
                        color = Color.White,
                        modifier = Modifier.align(Alignment.BottomCenter).padding(8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp)) // Espacio entre la imagen y el texto

                // Campo para editar el nombre
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.Gray,
                        //textColor = Color.White
                    )
                )

                // Campo para editar la biografía
                OutlinedTextField(
                    value = bio,
                    onValueChange = { bio = it },
                    label = { Text("Bio") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.Gray,
                        //textColor = Color.White
                    )
                )

                // Campo para editar la edad con un selector de fecha
                OutlinedTextField(
                    value = age,
                    onValueChange = { },
                    label = { Text("Age") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            datePickerDialog.show()
                        },
                    enabled = false, // No editable directamente, solo mediante el calendario
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.Gray,
                       // textColor = Color.White
                    )
                )

                // Selector para perfil privado/público
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    Text("Private Profile", color = Color.White, modifier = Modifier.weight(1f))
                    Switch(
                        checked = isPrivate,
                        onCheckedChange = { isPrivate = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = MaterialTheme.colorScheme.primary,
                            uncheckedThumbColor = Color.Gray
                        )
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Botones de Cancelar y Guardar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = onCancelClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Cancel")
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = onSaveClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        ),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Save")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEditUserProfileScreen() {
    EditUserProfileScreen(
        onSaveClick = {},
        onCancelClick = {}
    )
}