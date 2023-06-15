package com.litmus7.loginandsignupwithjetpackcompose.screens

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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.ImeOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.litmus7.loginandsignupwithjetpackcompose.routes.Routes
import com.litmus7.loginandsignupwithjetpackcompose.ui.theme.LoginAndSignUpWithJetpackComposeTheme
import com.litmus7.loginandsignupwithjetpackcompose.ui.theme.Purple700

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginPage(navController: NavHostController) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val mContext = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {
        ClickableText(
            text = AnnotatedString("Sign up here"),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp),
            onClick = { navController.navigate(Routes.SignUp.route) },
            style = TextStyle(
                fontSize = 17.sp,
                fontFamily = FontFamily.Default,
                textDecoration = TextDecoration.Underline,
                color = Purple700
            )
        )
    }
    Column(
        modifier = Modifier.padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val username = remember { mutableStateOf(TextFieldValue()) }
        val password = remember { mutableStateOf(TextFieldValue()) }

        Text(text = "Login", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Serif))

        Spacer(modifier = Modifier.height(30.dp))
        TextField(
            label = { Text(text = "Username") },
            value = username.value,
            onValueChange = { username.value = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        Spacer(modifier = Modifier.height(30.dp))
        TextField(
            label = { Text(text = "Password") },
            value = password.value,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }),
            onValueChange = { password.value = it })

        Spacer(modifier = Modifier.height(50.dp))
        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
            Button(
                onClick = { validateLoginField(mContext,username,password,navController) },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "Login")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        ClickableText(
            text = AnnotatedString("Forgot password?"),
            onClick = { navController.navigate(Routes.ForgotPassword.route) },
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily.Default
            )
        )
    }
}

fun validateLoginField(mContext: Context, username: MutableState<TextFieldValue>, password: MutableState<TextFieldValue>, navController: NavHostController) {
    if(username.value.text.isEmpty() and password.value.text.isNotEmpty()){
        Toast.makeText(mContext, "Username is Empty", Toast.LENGTH_SHORT).show()
    }
    if (password.value.text.isEmpty() and username.value.text.isNotEmpty()){
        Toast.makeText(mContext, "Password is Empty", Toast.LENGTH_SHORT).show()
    }
    if(username.value.text.isEmpty() and password.value.text.isEmpty()){
        Toast.makeText(mContext, "Username and Password are Empty", Toast.LENGTH_SHORT).show()
    }
    if(username.value.text.isNotEmpty() and password.value.text.isNotEmpty()){
        navController.navigate(Routes.HomeScreen.route )
        Toast.makeText(mContext, "Successfully Validated", Toast.LENGTH_SHORT).show()
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    LoginAndSignUpWithJetpackComposeTheme {
        LoginPage(navController = navController)
    }
}