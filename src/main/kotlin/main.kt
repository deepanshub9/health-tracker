import controllers.UserStore
import models.User




//var user = User()

val userStore = UserStore()




fun main(){
    println("Welcome to Health Tracker")
    runApp()
}

fun addUser() {
    val user = User()
    println("Please enter the following for the user:")
    print("    Name: ")

    user.name = readln()


    print("    Email: ")
    user.email = readln()



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

    userStore.create(user)
    //userStore.create(getUserDetails())

}

fun listUsers() {
    val users = userStore.findAll()                             // Fetches all users
    if (users.isNotEmpty()) {
        println("The user details are:")
        users.forEach { user ->
            println(user)
        }
    } else {
        println("No users found.")
    }
}

fun menu(): Int{
    print("""
        |Main Menu:
        |  1. Add User
        |  2. List User
        |  3. Search by Id
        |  4. Delete
        |  5. updateUser
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
            2 -> listUsers()
            3 -> searchById()
            4 -> deleteUser()
            in(4..6) -> println("Feature coming soon")
            0 -> println("Bye...")
            else -> print("Invalid Option")
        }
    } while (input != 0)
}

fun getUserById() : User?{
    print("Enter the id of the user: ")
    return  userStore.findOne(readlnOrNull()?.toIntOrNull() ?: -1)
}

fun searchById() {
    val user = getUserById()
    if (user == null)
        println ("No user found")
    else
        println(user)
}

fun deleteUser(){
    if (userStore.delete(getUserById()))
        println ("User deleted")
    else
        println ("No user")
}













