package com.example.sovkombank_team_challenge_it_amnezia.widgets.pinView

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.multidots.fingerprintauth.FingerPrintAuthHelper
import kotlinx.android.synthetic.main.item_number.view.*
import kotlinx.android.synthetic.main.pin_view.view.*
import kotlin.properties.Delegates


class PinView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    var setOnCompletedListener: (pinCode: String) -> Unit = {}

    var setOnPinKeyClickListener : (keyPressed : String) -> Unit = {}

    private lateinit var attributes: TypedArray

    private var currentPinCode by Delegates.observable("") { _, _, newValue ->
        val drawable = DrawableCompat.wrap(resources.getDrawable(R.drawable.oval, null))
        DrawableCompat.setTint(
            drawable, attributes.getColor(
                R.styleable.PinView_dotProgressColor,
                Color.BLACK
            )
        )
        if (newValue.length >= 0) initPinCodeProgress()
        if (newValue.isNotEmpty()) pinOneProgress.background = drawable
        if (newValue.length >= 2) pinTwoProgress.background = drawable
        if (newValue.length >= 3) pinThreeProgress.background = drawable
        if (newValue.length >= 4) pinFourProgress.background = drawable

    }

    init {
        inflate(context, R.layout.pin_view, this)
        attributes = context.obtainStyledAttributes(attrs, R.styleable.PinView)

        titleTextView.text = attributes.getString(R.styleable.PinView_titleName)

        titleTextView.textSize = 16F

        titleTextView.setTextColor(
            attributes.getColor(
                R.styleable.PinView_titleTextColor,
                Color.BLACK
            )
        )

        initPinCodeProgress()

        pinOneProgress.layoutParams.width =
            attributes.getDimensionPixelSize(R.styleable.PinView_dotRadius, 30)
        pinOneProgress.layoutParams.height =
            attributes.getDimensionPixelSize(R.styleable.PinView_dotRadius, 30)

        pinTwoProgress.layoutParams.width =
            attributes.getDimensionPixelSize(R.styleable.PinView_dotRadius, 30)
        pinTwoProgress.layoutParams.height =
            attributes.getDimensionPixelSize(R.styleable.PinView_dotRadius, 30)

        pinThreeProgress.layoutParams.width =
            attributes.getDimensionPixelSize(R.styleable.PinView_dotRadius, 30)
        pinThreeProgress.layoutParams.height =
            attributes.getDimensionPixelSize(R.styleable.PinView_dotRadius, 30)

        pinFourProgress.layoutParams.width =
            attributes.getDimensionPixelSize(R.styleable.PinView_dotRadius, 30)
        pinFourProgress.layoutParams.height =
            attributes.getDimensionPixelSize(R.styleable.PinView_dotRadius, 30)

        numbersGridView.adapter = NumbersAdapter(attributes)

        errorTextView.text = attributes.getString(R.styleable.PinView_errorMessageText)

        errorTextView.textSize = 14f

        errorTextView.setTextColor(
            attributes.getColor(
                R.styleable.PinView_errorMessageColor,
                Color.RED
            )
        )
    }

    private fun initPinCodeProgress() {

        val drawable = DrawableCompat.wrap(resources.getDrawable(R.drawable.oval, null))
        DrawableCompat.setTint(
            drawable, attributes.getColor(
                R.styleable.PinView_dotUnProgressColor,
                Color.LTGRAY
            )
        )

        pinOneProgress.background = drawable
        pinTwoProgress.background = drawable
        pinThreeProgress.background = drawable
        pinFourProgress.background = drawable
    }

    fun deleteLastPin() {
        if (currentPinCode.isNotEmpty())
            currentPinCode = currentPinCode.dropLast(1)
    }

    fun clearPin() {
        currentPinCode = ""
    }

    fun showError(isEnabled : Boolean){
        if(isEnabled)
        {
            errorTextView.visibility = View.VISIBLE
        }
        else
        {
            errorTextView.visibility = View.GONE
        }
    }

    fun isErrorVisible (): Boolean {
        return errorTextView.isVisible
    }

    private fun appendNumber(number: Int) {
        if (currentPinCode.length < 3)
        {
            currentPinCode = currentPinCode.plus(number)
            setOnPinKeyClickListener(number.toString())
        }
        else if (currentPinCode.length == 3) {
            currentPinCode = currentPinCode.plus(number)
            setOnCompletedListener(currentPinCode)
        }

    }

    private inner class NumbersAdapter(private val attributes: TypedArray) :
        RecyclerView.Adapter<NumbersAdapter.ViewHolder>() {

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_number, parent, false)
            return ViewHolder(view, attributes)
        }

        override fun getItemCount(): Int {
            return 12
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(holder, position)
        }

        inner class ViewHolder(itemView: View, attributes: TypedArray) :
            RecyclerView.ViewHolder(itemView) {
            private val numberTextView: TextView = itemView.numberTextView
            private val deleteImageView: ImageView = itemView.deleteImageView
            private val fingerprintImageView: ImageView = itemView.fingerprintImageView

            init {
                val fontSize =
                    attributes.getDimensionPixelSize(R.styleable.PinView_numbersTextSize, 64)
                itemView.numberTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize.toFloat())

                deleteImageView.layoutParams.width = fontSize
                deleteImageView.layoutParams.height = fontSize

                if (adapterPosition < 9) {
                    itemView.numberTextView.setTextColor(
                        attributes.getColor(
                            R.styleable.PinView_numbersTextColor,
                            resources.getColor(R.color.blue, null)
                        )
                    )
                } else
                    itemView.numberTextView.setTextColor(
                        attributes.getColor(
                            R.styleable.PinView_clearButtonColor,
                            resources.getColor(R.color.red, null)
                        )
                    )
            }

            fun bind(viewHolder: ViewHolder, position: Int) {
                val number = position + 1
                when {
                    position <= 8 -> {
                        viewHolder.apply {
                            numberTextView.text = number.toString()
                            itemView.setOnClickListener {
                                appendNumber(number)
                            }
                        }

                    }
                    position == 9 -> {
                        viewHolder.apply {
                            numberTextView.visibility = View.GONE
                            fingerprintImageView.visibility = View.VISIBLE
                            itemView.setOnClickListener {
                                setOnPinKeyClickListener("fingerprint")
                            }
                        }
                    }
                    position == 10 -> viewHolder.apply {
                        numberTextView.text = "0"
                        itemView.setOnClickListener {
                            appendNumber(0)
                            setOnPinKeyClickListener("0")
                        }
                    }
                    position == 11 -> viewHolder.apply {
                        numberTextView.visibility = View.GONE
                        deleteImageView.visibility = View.VISIBLE
                        itemView.setOnClickListener {
                            deleteLastPin()
                            setOnPinKeyClickListener("delete")
                        }
                    }
                }

            }
        }
    }

}
