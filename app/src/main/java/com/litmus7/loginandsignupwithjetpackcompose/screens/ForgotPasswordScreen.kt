package com.litmus7.loginandsignupwithjetpackcompose.screens

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.litmus7.loginandsignupwithjetpackcompose.appcomponent.CustomTopAppBar
import com.litmus7.loginandsignupwithjetpackcompose.routes.Routes
import com.litmus7.loginandsignupwithjetpackcompose.ui.theme.LoginAndSignUpWithJetpackComposeTheme

@Composable
fun ForgotPassword(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        ScaffoldWithTopBarForgotPass(navController)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScaffoldWithTopBarForgotPass(navController: NavHostController) {
    Scaffold(
        topBar = {
            CustomTopAppBar(navController, "Forgot Password", true)
        }, content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(50.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val username = remember { mutableStateOf(TextFieldValue()) }
                val context = LocalContext.current
                TextField(
                    label = { Text(text = "Username or Phone Number") },
                    value = username.value,
                    onValueChange = { username.value = it },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.height(30.dp))
                Button(onClick = { validateField(context,username,navController) },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .width(150.dp)
                        .height(50.dp)) {
                  Text(text = "Submit")
                }
            }
        })
}

fun validateField(context: Context, username: MutableState<TextFieldValue>,navController: NavHostController) {
    if(username.value.text.isEmpty()){
     Toast.makeText(context,"Please Enter Username or Phone Number",Toast.LENGTH_SHORT).show()
    }else{
     navController.popBackStack()
    }
}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordPreview() {
    val navController = rememberNavController()
    LoginAndSignUpWithJetpackComposeTheme {
        ForgotPassword(navController=navController)
    }
}