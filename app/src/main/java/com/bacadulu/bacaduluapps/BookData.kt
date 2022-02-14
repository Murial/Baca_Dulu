package com.bacadulu.bacaduluapps

import android.content.Context
import com.bacadulu.bacaduluapps.model.Books

fun booksList(context: Context): List<Books> {

    return listOf(
        Books(
            R.drawable.cover,
            context.getString(R.string.title_book1),
            "ini adalah data ini"
        )
    )
}