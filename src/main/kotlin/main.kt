import controllers.UserStore
import models.User




//var user = User()

val userStore = UserStore()




fun main(){
    println("Welcome to Health Tracker")
    runApp()
}

fun getUserDetails(): User {
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

    return user

}

fun addUser() {
    val user = getUserDetails()
    userStore.create(user)
    println("User added successfully.")
}

fun listUsers() {
    println("The user details are:")
    userStore.findAll()
        .sortedBy { it.name }
        .forEach{println(it)}
}



fun menu(): Int{
    print("""
        |Main Menu:
        |  1. Add User
        |  2. List User
        |  3. Search by Id
        |  4. Delete
        |  5. Update User
        |  6. Search by Gender
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
            5 -> updateUser()
            6 -> searchByGender()
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

private fun searchByGender() {
    val gender = readValidGender("Enter Gender (F, M, or O): ")
    userStore.findAll()
        .filter { it.gender.equals(gender, ignoreCase = true) }
        .sortedBy { it.name }
        .forEach { println(it) }
}

private fun readValidGender(prompt: String): Char {
    while (true) {
        print(prompt)
        val inputGender = readLine()?.uppercase()
        if (inputGender == "F" || inputGender == "M" || inputGender == "O") {
            return inputGender[0]
        } else {
            println("Invalid input. Please enter F, M, or O.")
        }
    }
}



fun deleteUser(){
    if (userStore.delete(getUserById()))
        println ("User deleted")
    else
        println ("No user")
}



fun updateUser() {
    listUsers()
    val foundUser = getUserById()

    if(foundUser != null) {
        //read in user data
        val user = getUserDetails()
        user.id = foundUser.id
        //using the id from foundUser and the details read from the console, update the user.
        if (userStore.update(user))
            println("User updated")
        else
            println("User not updated")
    }
    else
        println("User not found")
}









