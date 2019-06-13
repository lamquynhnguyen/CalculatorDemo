package com.example.calculatordemo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_display.*

class DisplayFragment : Fragment() {

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_display, container, false)
	}

	fun displayResult(result: String) {
		textViewDisplay.text = result
	}

	fun appendResult(result: String) {
		textViewDisplay.append(result)
	}

	fun takeText() = textViewDisplay.text.toString()
}
