package com.example.calculatordemo

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_input.*

class InputFragment : Fragment() {
	private lateinit var mainActivity: MainActivity
	private var mResult: Float = 0.0F
	private var mFirstValue: Float = 0.0F
	private var mSecondValue: Float = 0.0F
	private var mSum: Boolean = false
	private var mSubtract: Boolean = false
	private var mMultiply: Boolean = false
	private var mDivide: Boolean = false

	override fun onAttach(context: Context?) {
		super.onAttach(context)
		if (context is MainActivity)
			mainActivity = context
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_input, container, false)
	}

	override fun onResume() {
		super.onResume()
		onClickButton()
	}

	private fun onClickButton() {
		btn1.setOnClickListener {
			requestAppendResult("1")
		}
		btn2.setOnClickListener {
			requestAppendResult("2")
		}
		btn3.setOnClickListener {
			requestAppendResult("3")
		}
		btn4.setOnClickListener {
			requestAppendResult("4")
		}
		btn5.setOnClickListener {
			requestAppendResult("5")
		}
		btn6.setOnClickListener {
			requestAppendResult("6")
		}
		btn7.setOnClickListener {
			requestAppendResult("7")
		}
		btn8.setOnClickListener {
			requestAppendResult("8")
		}
		btn9.setOnClickListener {
			requestAppendResult("9")
		}
		btn0.setOnClickListener {
			requestAppendResult("0")
		}
		btnComma.setOnClickListener {
			if (this.mainActivity.takeText().contains("."))
				Toast.makeText(this.mainActivity, R.string.wrong_format, Toast.LENGTH_SHORT).show()
			else requestAppendResult(".")

		}
		btnSum.setOnClickListener {
			if (checkEmpty())
				Toast.makeText(this.mainActivity, R.string.no_input, Toast.LENGTH_SHORT).show()
			else {
				mFirstValue = convertToFloat()
				mSum = true
				mSubtract = false
				mMultiply = false
				mDivide = false
				requestDisplayResult("")
			}
		}
		btnSubtract.setOnClickListener {
			if (checkEmpty())
				Toast.makeText(this.mainActivity, R.string.no_input, Toast.LENGTH_SHORT).show()
			else {
				mFirstValue = convertToFloat()
				mSubtract = true
				mSum = false
				mMultiply = false
				mDivide = false
				requestDisplayResult("")
			}
		}
		btnMultiply.setOnClickListener {
			if (checkEmpty())
				Toast.makeText(this.mainActivity, R.string.no_input, Toast.LENGTH_SHORT).show()
			else {
				mFirstValue = convertToFloat()
				mMultiply = true
				mSubtract = false
				mSum = false
				mDivide = false
				requestDisplayResult("")
			}
		}
		btnDivide.setOnClickListener {
			if (checkEmpty())
				Toast.makeText(this.mainActivity, R.string.no_input, Toast.LENGTH_SHORT).show()
			else {
				mFirstValue = convertToFloat()
				mDivide = true
				mSubtract = false
				mMultiply = false
				mSum = false
				requestDisplayResult("")
			}
		}
		btnInterest.setOnClickListener {
			if (checkEmpty())
				Toast.makeText(this.mainActivity, R.string.no_input, Toast.LENGTH_SHORT).show()
			else {
				mFirstValue = convertToFloat()
				mResult = mFirstValue / 100.0F
				requestDisplayResult(mResult.toString())
			}
		}
		btnNegative.setOnClickListener {
			if (checkEmpty())
				Toast.makeText(this.mainActivity, R.string.no_input, Toast.LENGTH_SHORT).show()
			else {
				if (this.mainActivity.takeText()[0] == '-')
					requestDisplayResult(dropFirstChar())
				else requestDisplayResult(plusNegative())
			}
		}
		btnReset.setOnClickListener {
			requestDisplayResult("")
			mFirstValue = 0.0F
			mSecondValue = 0.0F
			mResult = 0.0F
			mSum = false
			mSubtract = false
			mMultiply = false
			mDivide = false
		}
		btnResult.setOnClickListener {
			if (checkEmpty())
				Toast.makeText(this.mainActivity, R.string.no_input, Toast.LENGTH_SHORT).show()
			else {
				mSecondValue = convertToFloat()
				if (mDivide && mSecondValue == 0.0F) {
					Toast.makeText(this.mainActivity, R.string.divide_0, Toast.LENGTH_SHORT).show()
					requestDisplayResult("")
				} else {
					if (mSum) {
						mResult = mFirstValue + mSecondValue
					} else if (mSubtract) {
						mResult = mFirstValue - mSecondValue
					} else if (mMultiply) {
						mResult = mFirstValue * mSecondValue
					} else if (mDivide) {
						mResult = mFirstValue / mSecondValue
					} else {
						mResult = mSecondValue
					}
					requestDisplayResult(mResult.toString())
				}
			}
		}
	}

	private fun requestAppendResult(result: String) {
		mainActivity.appendResult(result)
	}

	private fun requestDisplayResult(result: String) {
		mainActivity.displayResult(result)
	}

	private fun checkEmpty() = mainActivity.takeText().isEmpty()

	private fun convertToFloat() = mainActivity.takeText().toFloat()

	private fun dropFirstChar() = mainActivity.takeText().drop(1)

	private fun plusNegative() = "-".plus(mainActivity.takeText())

	fun getResult() = mResult
}
