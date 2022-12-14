
package com.example.sovkombank_team_challenge_it_amnezia.widgets.pincodeLayout

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.*
import android.view.animation.Animation
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ViewSwitcher
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.pin_code_layout.view.*
import kotlin.math.absoluteValue

class PinCodeLayout @JvmOverloads constructor(
	context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

	companion object {
		val TYPE_CAP_LETTERS_AND_DIGITS = charArrayOf(
			'A',
			'B',
			'C',
			'D',
			'E',
			'F',
			'G',
			'H',
			'I',
			'J',
			'K',
			'L',
			'M',
			'N',
			'O',
			'P',
			'Q',
			'R',
			'S',
			'T',
			'U',
			'V',
			'W',
			'X',
			'Y',
			'Z',
			'0',
			'1',
			'2',
			'3',
			'4',
			'5',
			'6',
			'7',
			'8',
			'9'
		)
		val TYPE_DIGITS = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
	}

	private var pinLength = 0
	private var pinType = 0
	private val defaultPinLength = 6
	private val alphaPropertyName = "alpha"
	private var animationDuration: Long = 0
	private val defaultAnimationDuration = 500
	private var alphaValueStart = 0f
	private var alphaValueEnd = 1f
	private val pinTypeDefault = 1

	private var pinTextColor = 0

	private var unfilledPinIcon = 0
	private var filledPinIcon = 0
	private var inputBackground = 0

	private var pinIsHidden = false

	private var pinInterface: PinCodeActions? = null

	private var objAnimator: ObjectAnimator? = null

	private var inactiveBarColor = 0
	private var activeBarColor = 0

	private var attributeArray = context.obtainStyledAttributes(attrs, R.styleable.PinCodeLayout, 0, 0)

	private val actionCallback = object : ActionMode.Callback {
		override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
			return false
		}

		override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
			return false
		}

		override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
			return false
		}

		override fun onDestroyActionMode(mode: ActionMode?) {
		}
	}

	private val focusChangeListener = object : OnFocusChangeListener {
		override fun onFocusChange(v: View?, hasFocus: Boolean) {
			v as EditText

			if (hasFocus) {
				bottomBar.setBackgroundColor(activeBarColor)
			}

			if (!hasFocus && v.text.length == pinLength) {
				pinInterface?.onPinFilled()
			}

			if (!hasFocus) {
				bottomBar.setBackgroundColor(inactiveBarColor)
				endAnimatePin()
				if (v.text.length == pinLength) {
					objAnimator?.end()
					objAnimator?.cancel()
				}

				return
			}

			if (v.text.isEmpty()) {
				animatePin(pinLinearLayout[0])
				return
			}

			if (v.text.length == pinLength) {
				animatePin(pinLinearLayout[v.text.length - 1])
			} else {
				animatePin(pinLinearLayout[v.text.length])
			}

		}
	}

	 val textWatcher = object : TextWatcher {
		var textLengthBefore = 0
		var textBeforeChange = ""
		var textResetManually = false

		override fun afterTextChanged(s: Editable?) {
		}

		override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
			if (textResetManually) {
				return
			}
			textLengthBefore = s?.length ?: 0
			textBeforeChange = s.toString()
		}

		override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

			s?.let {
				if (before == 1
					&& count == 0
				) {
					clearPin(it.lastIndex + 1)
					pinInterface?.onPinCleared()
					return
				}

				if (!(it.isBlank() || isValidInput(it.last()))
					|| before == count
				) {
					return
				}

				// If input is bigger than 1 character
				if (textLengthBefore.minus(it.length).absoluteValue > 1) {
					// If no input is inside, fill first available chars (e.g. after rotation recreation)
					if (textLengthBefore == 0) {
						val endIndex = if (it.length > pinLength) pinLength else it.length
						it.substring(0, endIndex).forEachIndexed { i, c ->
							fillPin(i, c.toString())
						}
					} else {
						// If already input is inside, dont allow set more than one char. Reset textview to old state
						textResetManually = true
						invisibleTextInput.setText(textBeforeChange)
						invisibleTextInput.setSelection(textLengthBefore)
						return
					}
					return
				}

				if (it.length == pinLength) {
					fillPin(it.lastIndex, it.last().toString())
					closeKeyboard()
					pinInterface?.onPinEntered(it.toString())
					return
				}

				if (textLengthBefore < it.length) {
					fillPin(it.lastIndex, it.last().toString())
				} else {
					clearPin(it.lastIndex + 1)
					pinInterface?.onPinCleared()
				}
			}
		}
	}

	init {
		getAttributes()
		when (pinType) {
			1 -> inflate(context, R.layout.pin_code_layout, this)
			else -> inflate(context, R.layout.pin_code_layout_digits, this)
		}
		setupEditText()
		addPins()
		setUpBottomBar()

		isFocusableInTouchMode = false
		isFocusable = true
	}

	private fun setUpBottomBar() {
		bottomBar.setBackgroundColor(inactiveBarColor)
	}

	 fun setupEditText() {
		invisibleTextInput.apply {
			filters = arrayOf(InputFilter.LengthFilter(pinLength), InputFilter.AllCaps())
			text?.clear()
			addTextChangedListener(textWatcher)
			text?.clear()
			customSelectionActionModeCallback = actionCallback
			onFocusChangeListener = focusChangeListener
			background = ContextCompat.getDrawable(context, inputBackground)
		}
	}

	private fun addPins() {
		for (x in 0 until pinLength) {
			pinLinearLayout.addView(Pin(context).apply {
				findViewById<View>(R.id.pinIconView).background =
					ContextCompat.getDrawable(context, unfilledPinIcon)
			})
		}
	}

	private fun getAttributes() {

		pinLength = attributeArray.getInteger(R.styleable.PinCodeLayout_pinLength, defaultPinLength)
		pinType = attributeArray.getInteger(R.styleable.PinCodeLayout_pinType, pinTypeDefault)

		animationDuration =
			attributeArray.getInteger(
				R.styleable.PinCodeLayout_animationDuration,
				defaultAnimationDuration
			).toLong()

		pinTextColor = attributeArray.getColor(
			R.styleable.PinCodeLayout_pinTextColor,
			ContextCompat.getColor(context, R.color.black)
		)

		pinIsHidden =
			attributeArray.getBoolean(R.styleable.PinCodeLayout_hidePin, pinIsHidden)

		inputBackground = attributeArray.getResourceId(
			R.styleable.PinCodeLayout_inputBackground,
			R.drawable.pin_background
		)

		unfilledPinIcon =
			attributeArray.getResourceId(
				R.styleable.PinCodeLayout_unfilledPinIcon,
				R.drawable.pincode_empty
			)

		filledPinIcon = attributeArray.getResourceId(
			R.styleable.PinCodeLayout_filledPinIcon,
			R.drawable.pincode_filled
		)

		activeBarColor = ContextCompat.getColor(
			context,
			attributeArray.getResourceId(
				R.styleable.PinCodeLayout_activeBarColor,
				R.color.blue
			)
		)

		inactiveBarColor = ContextCompat.getColor(
			context,
			attributeArray.getResourceId(
				R.styleable.PinCodeLayout_inactiveBarColor,
				R.color.lightGrey
			)
		)
		attributeArray.recycle()
	}

	private fun closeKeyboard() {
		val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
		imm.hideSoftInputFromWindow(invisibleTextInput.windowToken, 0)
		invisibleTextInput.clearFocus()
	}

	private fun fillPin(index: Int, char: CharSequence) {
		val pin: ViewGroup = pinLinearLayout[index] as ViewGroup
		val currentPin: ViewSwitcher = pin[0] as ViewSwitcher

		if (!pinIsHidden) {
			val pinText: MaterialTextView = currentPin[1] as MaterialTextView
			pinText.text = char
			pinText.setTextColor(pinTextColor)

			if (!currentPin[1].isShown) {
				currentPin.showNext()
			}
		} else {
			currentPin[0].background = ContextCompat.getDrawable(context, filledPinIcon)
		}

		endAnimatePin()
		if (index < pinLength - 1) {
			val nextPinGroup: ViewGroup = pinLinearLayout[index + 1] as ViewGroup
			val nextPin: ViewSwitcher = nextPinGroup[0] as ViewSwitcher
			animatePin(nextPin)
		}
	}

	 fun clearPin(index: Int) {
		val pin: ViewGroup = pinLinearLayout[index] as ViewGroup
		val currentPin: ViewSwitcher = pin[0] as ViewSwitcher
		if (!pinIsHidden && currentPin[1].isShown) {
			currentPin.showPrevious()
		} else {
			currentPin[0].background = ContextCompat.getDrawable(context, unfilledPinIcon)
		}
		endAnimatePin()
		animatePin(pin)
	}

	private fun animatePin(mCircle: View) {
		objAnimator = ObjectAnimator.ofFloat(mCircle, alphaPropertyName, alphaValueStart, alphaValueEnd)
		objAnimator?.run {
			duration = animationDuration
			repeatMode = ValueAnimator.REVERSE
			repeatCount = Animation.INFINITE
			start()
		}
	}

	fun endAnimatePin() {
		objAnimator?.end()
		objAnimator?.cancel()
	}

	fun setCallback(pinInterface: PinCodeActions) {
		this.pinInterface = pinInterface
	}

	fun setUnfilledPinIcon(@DrawableRes pinResourceId: Int) {
		unfilledPinIcon = pinResourceId
		for (x in 0 until pinLength) {
		pinLinearLayout[x].findViewById<View>(R.id.pinIconView).background =
				ContextCompat.getDrawable(context, unfilledPinIcon)
		}
	}

	fun setFilledPinIcon(@DrawableRes pinResourceId: Int) {
		filledPinIcon = pinResourceId
	}

	fun setInputBackground(@DrawableRes backgroundId: Int) {
		inputBackground = backgroundId
		invisibleTextInput.background = ContextCompat.getDrawable(context, inputBackground)
	}

	fun setActiveBarColor(@ColorRes colorId: Int) {
		activeBarColor = ContextCompat.getColor(context, colorId)
	}

	fun setPinTextColor(@ColorRes colorId: Int) {
		pinTextColor = ContextCompat.getColor(context, colorId)
	}

	fun setInActiveBarColor(@ColorRes colorId: Int) {
		inactiveBarColor = ContextCompat.getColor(context, colorId)
	}

	fun setAnimationDuration(duration: Long) {
		animationDuration = duration
	}

	fun setHiddenState(hiddenState: Boolean) {
		pinIsHidden = hiddenState

		for (x in 0 until (invisibleTextInput.text?.length ?: 0)) {
			val pin: ViewGroup = pinLinearLayout[x] as ViewGroup
			val currentPin: ViewSwitcher = pin[0] as ViewSwitcher
			if (pinIsHidden) {
				currentPin[0].background = ContextCompat.getDrawable(context, filledPinIcon)
				if (currentPin[1].isShown) currentPin.showPrevious()
			} else {
				currentPin[0].background = ContextCompat.getDrawable(context, unfilledPinIcon)
				if (currentPin[0].isShown) currentPin.showNext()
			}

		}
	}

	private fun isValidInput(char: Char?): Boolean {
		if (char == null) {
			return false
		}
		return when (pinType) {
			1 -> TYPE_CAP_LETTERS_AND_DIGITS.contains(char)
			else -> TYPE_DIGITS.contains(char)
		}
	}
}