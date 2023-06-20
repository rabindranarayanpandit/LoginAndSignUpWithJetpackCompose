package com.devskill.loginandsignupwithjetpackcompose.routes

sealed class Routes(val route: String) {
    object Login : Routes("Login")
    object SignUp : Routes("SignUp")
    object  ForgotPassword: Routes("ForgotPassword")
    object  HomeScreen : Routes("HomeScreen")
}
