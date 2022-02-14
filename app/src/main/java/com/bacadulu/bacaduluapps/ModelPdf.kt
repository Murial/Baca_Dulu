package com.bacadulu.bacaduluapps

class ModelPdf {
    var uid:String = ""
    var id:String = ""
    var title:String = ""
    var description:String = ""
    var category:String = ""
    var url:String = ""
    var timestamp:Long = 0
    var viewsCount:Long = 0
    var downloadsCount:Long = 0

    //empty constructor (required for firebase)
    constructor()

    //parameterized constructor
    constructor(
        uid:String,
        id:String,
        title:String,
        description:String,
        category:String,
        url:String,
        timestamp:Long,
        viewsCount:Long,
        downloadsCount:Long
    ){
        this.uid = uid
        this.id = id
        this.title = title
        this.description = description
        this.category = category
        this.url = url
        this.timestamp = timestamp
        this.viewsCount = viewsCount
        this.downloadsCount = downloadsCount
    }

}