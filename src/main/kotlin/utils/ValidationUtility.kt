package utils

class ValidationUtility {


    //for_Gender
    fun isValidGender(gender: Char): Boolean {
        return gender == 'F' || gender == 'M' || gender == 'O'
    }

    //for_Email
    fun isValidEmail(email: String): Boolean {
        val emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        return email.matches(emailPattern)
    }

    //for_Weight
    fun isValidWeight(weight: Double): Boolean {
        return weight in 0.0..300.0
    }

    //for_Height
    fun isValidHeight(height: Float): Boolean {
        return height in 0.5f..2.5f
    }


}