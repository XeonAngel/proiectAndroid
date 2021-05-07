package com.example.androidproject.models

class FirebaseMovie : DbObject {
    override var id: Int = 0
    var name: String = ""
    var category: String = ""

    constructor() {}

    constructor(id: Int, name: String, category: String) {
        this.id = id
        this.name = name
        this.category = category
    }

}