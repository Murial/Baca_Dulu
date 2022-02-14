package com.bacadulu.bacaduluapps
import android.content.Context
import android.content.Intent
import android.graphics.pdf.PdfDocument
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bacadulu.bacaduluapps.databinding.ActivityLoginBinding
import com.bacadulu.bacaduluapps.databinding.RowPdfBinding


class AdapterPdf : RecyclerView.Adapter<AdapterPdf.HolderPdf> {
    //get context through constructor
    private var context: Context

    //get arraylist to hold PDFs from constructor
    private var pdfArrayList: ArrayList<ModelPdf>

    //viewbinding row_pdf
    private lateinit var binding : RowPdfBinding




    constructor(context: Context, pdfArrayList: ArrayList<ModelPdf>) {
        this.context = context
        this.pdfArrayList = pdfArrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderPdf {
        //inflate row_pdf
        binding = RowPdfBinding.inflate(LayoutInflater.from(context), parent, false)

        return HolderPdf(binding.root)
    }

    override fun onBindViewHolder(holder: HolderPdf, position: Int) {
        val model = pdfArrayList[position]
        val bookId = model.id
        val categoryId = model.category
        val title = model.title
        val description = model.description
        val uid = model.uid
        val url = model.url
        val timestamp = model.timestamp

//        val binding = RowPdfBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        //convert time
        val date = MyApplication.formatTimeStamp(timestamp)

        //set data


        MyApplication.loadPdfFromUrlSinglePage(url, title, pdfView, null)



//        MyApplication.loadPdfSize(url, title, pdfSize)

        //handle click, open pdf detail
//        holder.itemView.setOnClickListener{
//            //pass book id
//            val intent = Intent(context, PdfDetailActivity::class.java)
//            intent.putExtra("bookId", bookId)
//            context.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int {
        return pdfArrayList.size //return list size
    }

    //viewholder class row_pdf
    inner class HolderPdf(itemView: View): RecyclerView.ViewHolder(itemView)
    //init UI component of row_pdf
    var pdfView = binding.pdfView
    var pdfTitle = binding.pdfTitle
    var pdfDesc = binding.pdfDesc
    var pdfCategory = binding.pdfCategory
    var pdfSize = binding.pdfSize
    var pdfDate = binding.pdfDate

}