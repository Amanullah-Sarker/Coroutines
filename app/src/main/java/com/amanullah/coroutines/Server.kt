package com.amanullah.coroutines

class Server {
    fun data1(): String{
        val name: String = "Amanullah Sarker"
        val age: Int = 23
        val gender: String = "Male"

        return "Name = $name"+"\nAge = $age"+"\nGender = $gender"
    }

    fun data2(): String{
        val name: String = "Tresan Ahmed"
        val age: Int = 24
        val gender: String = "Male"

        return "Name = $name"+"\nAge = $age"+"\nGender = $gender"
    }

    fun data3(): String{
        val name: String = "Masum Ara"
        val age: Int = 24
        val gender: String = "Female"

        return "Name = $name"+"\nAge = $age"+"\nGender = $gender"
    }
}