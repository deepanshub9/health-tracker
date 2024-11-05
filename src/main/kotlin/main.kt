import models.User

var user = User()

fun main(){
    println("Welcome to Health Tracker")
    runApp()
}
fun addUser() {
    println("Please enter the following for the user:")
    print("    Name: ")
    user.name = readln()


    print("    Email: ")
    user.email = readln()

    print("    Id: ")
    user.id = readlnOrNull()?.toIntOrNull() ?: -1


    print("    Weight (kgs): ")
    user.weight = readLine()?.toDoubleOrNull() ?: 0.0



    print("    Height (meters): ")
    user.height = readLine()?.toFloatOrNull() ?: 0.0f



    while (true) {
        print("    Gender (F for Female, M for Male, O for Other): ")
        val inputGender = readLine()?.uppercase()
        if (inputGender == "F" || inputGender == "M" || inputGender == "O") {
            user.gender = inputGender[0]
            break
        } else {
            println("Invalid input. Please enter F, M, or O.")
        }
    }


}
fun listUser(){
    print("The user details are: $user")
}

fun menu(): Int{
    print("""
        |Main Menu:
        |  1. Add User
        |  2. List User
        |  0. Exit
        |Please enter your option: """.trimMargin())
    return readlnOrNull()?.toIntOrNull() ?: -1
}

fun runApp(){
    var input: Int
    do {
        input = menu()
        when(input) {
            1 -> addUser()
            2 -> listUser()
            in(3..6) -> println("Feature coming soon")
            0 -> println("Bye...")
            else -> print("Invalid Option")
        }
    } while (input != 0)
}















