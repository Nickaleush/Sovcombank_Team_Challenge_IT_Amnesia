//package com.example.sovkombank_team_challenge_it_amnezia.ui.client.home
//
//import android.os.Build
//import android.util.TypedValue
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.annotation.RequiresApi
//import androidx.core.widget.TextViewCompat
//import androidx.recyclerview.widget.RecyclerView
//import com.example.sovkombank_team_challenge_it_amnezia.R
//
//class HomeBillsAdapter(private val bullsList: MutableList<FullPetInfo> ) : RecyclerView.Adapter<UserProfileAdapter.ViewHolder>() {
//
//    private lateinit var mListener: OnClickListener
//
//    class ViewHolder(itemView: View, listener: OnClickListener) : RecyclerView.ViewHolder(itemView) {
//        val addPetImageView: ImageView = itemView.findViewById(R.id.addPetImageList)
//        val chainLeft: ImageView = itemView.findViewById(R.id.chain_1)
//        val chainRight: ImageView = itemView.findViewById(R.id.chain_2)
//        val petNameTextview: TextView = itemView.findViewById(R.id.petName)
//        //        val gradient: AppCompatImageView = itemView.findViewById(R.id.gradient_imageview)
//        init {
//            itemView.cardview3.setOnClickListener {
//                listener.onClick(absoluteAdapterPosition)
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pet_list, parent, false)
//        return ViewHolder(view, mListener)
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val v = holder.itemView
//        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
//            holder.petNameTextview, 15,
//            20, 1, 0)
//        holder.petNameTextview.setAutoSizeTextTypeUniformWithConfiguration(
//            15, 20, 1, TypedValue.COMPLEX_UNIT_DIP)
//        holder.petNameTextview.visibility = View.VISIBLE
//        holder.chainLeft.visibility = View.VISIBLE
//        holder.chainRight.visibility = View.VISIBLE
//        val item = petList[position]
//        v.petName.text = item.name
////        holder.gradient.setBackgroundResource(R.drawable.cardview_gradient_background)
//        when (item.avatarUrl) {
//            null ->  Glide.with(holder.itemView.context)
//                .load(R.drawable.exit)
//                .fitCenter()
//                .signature(ObjectKey(PET_AVATAR_URL))
//                .into(holder.addPetImageView)
//            else -> Glide.with(holder.itemView.context)
//                .load(item.avatarUrl)
//                .fitCenter()
//                .placeholder(R.drawable.progress_animation)
//                .error(R.drawable.shape_placeholder)
//                .signature(ObjectKey(PET_AVATAR_URL))
//                .into(holder.addPetImageView)
//
//        }
////        applyBlur(holder.addPetImageView, holder.petNameTextview, holder)
//        if(position == 0) {
//            holder.chainLeft.visibility = View.GONE
//            holder.chainRight.visibility = View.GONE
//            holder.itemView.petName.visibility = View.GONE
//            Glide.with(holder.itemView.context)
//                .load(R.drawable.group_177)
//                .fitCenter()
//                .into(holder.addPetImageView)
//        }
//    }
//
////    private fun applyBlur(image: ImageView, petNameTextview: TextView, holder: ViewHolder) {
////        image.viewTreeObserver
////            .addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
////                override fun onPreDraw(): Boolean {
////                    image.viewTreeObserver.removeOnPreDrawListener(this)
////                    image.buildDrawingCache()
////                    val bmp: Bitmap = image.drawingCache
////                    blur(bmp, petNameTextview, holder)
////                    return true
////                }
////            })
////    }
////
////    private fun blur(bkg: Bitmap, view: View, holder: ViewHolder) {
////        val scaleFactor = 1f
////        val radius = 20f
////        var overlay = Bitmap.createBitmap(
////            (view.measuredWidth / scaleFactor).toInt(),
////            (view.measuredHeight / scaleFactor).toInt(), Bitmap.Config.ARGB_8888
////        )
////        val canvas = Canvas(overlay!!)
////        canvas.translate(-view.left / scaleFactor, -view.top / scaleFactor)
////        canvas.scale(1 / scaleFactor, 1 / scaleFactor)
////        val paint = Paint()
////        paint.flags = Paint.FILTER_BITMAP_FLAG
////        canvas.drawBitmap(bkg, 0f, 0f, paint)
////        overlay = FastBlur.doBlur(overlay, radius.toInt(), true)
////        view.background = BitmapDrawable(holder.itemView.resources, overlay)
////    }
//
//    interface OnClickListener {
//        fun onClick(position: Int)
//    }
//
//    fun setOnClickRecyclerListener(listener: OnClickListener){
//        mListener = listener
//    }
//
//    override fun getItemCount() = petList.size
//}
