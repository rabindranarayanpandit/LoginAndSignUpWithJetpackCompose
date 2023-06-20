package com.techskill.loginandsignupwithjetpackcompose.screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.techskill.loginandsignupwithjetpackcompose.appcomponent.CustomTopAppBar
import com.techskill.loginandsignupwithjetpackcompose.routes.Routes
import com.techskill.loginandsignupwithjetpackcompose.ui.theme.LoginAndSignUpWithJetpackComposeTheme

@Composable
fun SignUp(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        ScaffoldWithTopBar(navController)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ScaffoldWithTopBar(navController: NavHostController) {
    val mContext = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold(
        topBar = {
            CustomTopAppBar(navController, "Signup", true)
        }, content = {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val username = remember { mutableStateOf(TextFieldValue()) }
                val phone = remember { mutableStateOf(TextFieldValue()) }
                val password = remember { mutableStateOf(TextFieldValue()) }
                val confirmPassword = remember { mutableStateOf(TextFieldValue()) }
                Text(text = "Sign Up", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Serif))
                Spacer(modifier = Modifier.height(30.dp))

                TextField(
                    label = { Text(text = "Username") },
                    value = username.value,
                    onValueChange = { username.value = it },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.height(30.dp))
                TextField(
                    label = { Text(text = "Phone") },
                    value = phone.value,
                    onValueChange = { phone.value = it },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.height(30.dp))
                TextField(
                    label = { Text(text = "Password") },
                    value = password.value,
                    onValueChange = { password.value = it },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.height(30.dp))
                TextField(
                    label = { Text(text = "Confirm Password") },
                    value = confirmPassword.value,
                    onValueChange = { confirmPassword.value = it },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }),
                )

                Spacer(modifier = Modifier.height(50.dp))
                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                        onClick = { validateSignUpField(mContext,username,phone,password,confirmPassword,navController) },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text = "Submit")
                    }
                }
            }
        })
}

fun validateSignUpField(mContext: Context, username: MutableState<TextFieldValue>, phone: MutableState<TextFieldValue>, password: MutableState<TextFieldValue>, confirmPassword: MutableState<TextFieldValue>, navController: NavHostController) {
    if(username.value.text.isEmpty() and phone.value.text.isEmpty()
        and password.value.text.isEmpty() and confirmPassword.value.text.isEmpty()){
        Toast.makeText(mContext, "Please Enter All Your Input Fields", Toast.LENGTH_SHORT).show()
    }
    if(username.value.text.isEmpty() and phone.value.text.isNotEmpty()
        and password.value.text.isNotEmpty() and confirmPassword.value.text.isNotEmpty()){
        Toast.makeText(mContext, "Please Enter Username", Toast.LENGTH_SHORT).show()
    }
    if(username.value.text.isNotEmpty() and phone.value.text.isEmpty()
        and password.value.text.isNotEmpty() and confirmPassword.value.text.isNotEmpty()){
        Toast.makeText(mContext, "Please Enter Phone Number", Toast.LENGTH_SHORT).show()
    }
    if(username.value.text.isNotEmpty() and phone.value.text.isNotEmpty()
        and password.value.text.isEmpty() and confirmPassword.value.text.isNotEmpty()){
        Toast.makeText(mContext, "Please Enter Password", Toast.LENGTH_SHORT).show()
    }

    if(username.value.text.isNotEmpty() and phone.value.text.isNotEmpty()
        and password.value.text.isNotEmpty() and confirmPassword.value.text.isEmpty()){
        Toast.makeText(mContext, "Please Enter Confirm Password", Toast.LENGTH_SHORT).show()
    }
    if(username.value.text.isNotEmpty() and phone.value.text.isEmpty()
        and password.value.text.isEmpty() and confirmPassword.value.text.isEmpty()){
        Toast.makeText(mContext, "Please Enter Phone Number,Password,Confirm Password", Toast.LENGTH_SHORT).show()
    }
    if(username.value.text.isNotEmpty() and phone.value.text.isNotEmpty()
        and password.value.text.isEmpty() and confirmPassword.value.text.isEmpty()){
        Toast.makeText(mContext, "Please Enter Password,Confirm Password", Toast.LENGTH_SHORT).show()
    }
    if(username.value.text.isEmpty() and phone.value.text.isEmpty()
        and password.value.text.isNotEmpty() and confirmPassword.value.text.isEmpty()){
        Toast.makeText(mContext, "Please Enter Username,Phone Number", Toast.LENGTH_SHORT).show()
    }
    if(username.value.text.isEmpty() and phone.value.text.isNotEmpty()
        and password.value.text.isEmpty() and confirmPassword.value.text.isEmpty()){
        Toast.makeText(mContext, "Please Enter Username,Password,Confirm Password", Toast.LENGTH_SHORT).show()
    }
    if(username.value.text.isNotEmpty() and phone.value.text.isNotEmpty()
        and password.value.text.isNotEmpty() and confirmPassword.value.text.isNotEmpty()){
        navController.navigate(Routes.Login.route)
        Toast.makeText(mContext, "Successfully Signed", Toast.LENGTH_SHORT).show()
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    val navController = rememberNavController()
    LoginAndSignUpWithJetpackComposeTheme {
        SignUp(navController=navController)
    }
}