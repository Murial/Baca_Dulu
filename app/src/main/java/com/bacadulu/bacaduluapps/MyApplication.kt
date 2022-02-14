package com.bacadulu.bacaduluapps
import android.app.Application
import android.icu.util.Calendar
import android.text.format.DateFormat
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.github.barteksc.pdfviewer.PDFView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

import java.util.*

class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
    }

    companion object{
        //convert timestamp
        fun formatTimeStamp(timestamp: Long) : String {
            val cal = Calendar.getInstance(Locale.ENGLISH)
            cal.timeInMillis = timestamp
            //dd/mm/yyyy
            return DateFormat.format("dd/MM/yyyy", timestamp).toString()
        }

        //get pdf size
        fun loadPdfSize(pdfUrl: String?, pdfTitle:String, pdfSize: TextView){
            val TAG = "PDF_SIZE_TAG"

            //using url to fetch file and its metadata from firebase storage
            val ref = FirebaseStorage.getInstance().getReferenceFromUrl(pdfUrl.toString())
            ref.metadata
                .addOnSuccessListener { storageMetadata ->
                    Log.d(TAG, "loadPdfSize : got metadata")
                    val bytes = storageMetadata.sizeBytes.toDouble()
                    Log.d(TAG, "loadPdfSize : Size, Bytes $bytes")

                    //convert bytes to kb/mb
                    val kb = bytes/1024
                    val mb = kb/1024
                    if(mb >= 1){
                        pdfSize.text = "${String.format("$.2f", mb)} MB"
                    } else if (kb >= 1){
                        pdfSize.text = "${String.format("$.2f", kb)} KB"
                    } else {
                        pdfSize.text = "${String.format("$.2f", bytes)} bytes"
                    }
                }
                .addOnFailureListener{ e->
                    //failed to get metadata
                    Log.d(TAG, "loadPdfSize: Failed to get metadata due to ${e.message}")
                }
        }
        fun loadPdfFromUrlSinglePage(
            pdfUrl: String?,
            pdfTitle: String,
            pdfView: PDFView,
            progressBar: ProgressBar?,
//            pagesTv: TextView
        ){
            val TAG = "PDF_THUMBNAIL_TAG"

            //get file and metadata from url
            val ref = FirebaseStorage.getInstance().getReferenceFromUrl(pdfUrl.toString())
            ref.getBytes(Constants.MAX_BYTES_PDF)
                .addOnSuccessListener { bytes ->
                    Log.d(TAG, "loadPdfSize : Size, Bytes $bytes")
                    //set to pdfview
                    pdfView.fromBytes(bytes)
                        .pages(0)//show first page
                        .spacing(0)
                        .swipeHorizontal(false)
                        .enableSwipe(false)
                        .onError{t->
                            progressBar!!.visibility = View.INVISIBLE
                            Log.d(TAG, "loadPdfFromUrlSinglePage: ${t.message}")
                        }
                        .onPageError{ page, t ->

                            progressBar!!.visibility = View.INVISIBLE
                            Log.d(TAG, "loadPdfFromUrlSinglePage: ${t.message}")
                        }
                        .onLoad{ nbPages ->
                            //pdf loaded, set page count and thumbnail
                            progressBar!!.visibility = View.INVISIBLE
                        }
                }
                .addOnFailureListener{ e->
                    //failed to get metadata
                    Log.d(TAG, "loadPdfSize: Failed to get metadata due to ${e.message}")
                }
        }
    }

    fun loadCategory(category: String, pdfCategory: TextView){
        val ref = FirebaseDatabase.getInstance().getReference("Categories")
        ref.child(category)
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    //getcategory
                    val catergory = "${snapshot.child("category").value}"

                    //set category
                    pdfCategory.text = category
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
    }

}