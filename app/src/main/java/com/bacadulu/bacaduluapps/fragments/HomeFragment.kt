package com.bacadulu.bacaduluapps.fragments

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bacadulu.bacaduluapps.model.Book;
import com.bacadulu.bacaduluapps.recyclerview.BookAdapter;
//import com.bacadulu.bacaduluapps.recyclerview.BookCallback;
//import com.bacadulu.bacaduluapps.recyclerview.CustomItemAnimator;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bacadulu.bacaduluapps.R

import androidx.recyclerview.widget.LinearLayoutManager



class HomeFragment : Fragment() {

    private var rvBooks: RecyclerView? = null
    private var bookAdapter: BookAdapter? = null
    private var mdata: List<Book>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home);
        initViews()
        initmdataBooks()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun initViews() {
        rvBooks = getView()?.findViewById(R.id.rv_book)
        rvBooks!!.layoutManager = LinearLayoutManager(this)
        rvBooks!!.setHasFixedSize(true)
    }

    private fun setupBookAdapter() {
        bookAdapter = BookAdapter(mdata)
        rvBooks!!.adapter = bookAdapter
    }

    private fun initmdataBooks() {

        // for testing purpos I'm creating a random set of books
        // you may get your data from web service or firebase database.
        mdata = ArrayList()
        (mdata as ArrayList<Book>).add(Book(R.drawable.book1))
    }
}