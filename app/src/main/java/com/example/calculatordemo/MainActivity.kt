package com.example.calculatordemo

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast

const val PREF_RESULT: String = "PREF_RESULT"

class MainActivity : AppCompatActivity() {
	private lateinit var displayFragment: DisplayFragment
	private lateinit var inputFragment: InputFragment
	private var mTimesChangeTheme: Int = 0

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		displayFragment = supportFragmentManager
			.findFragmentById(R.id.fragmentDisplay) as DisplayFragment
		inputFragment = supportFragmentManager
			.findFragmentById(R.id.fragmentInput) as InputFragment
		printLastSavedResult()
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		val inflater: MenuInflater = menuInflater
		inflater.inflate(R.menu.main_menu, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		return when (item?.itemId) {
			R.id.menuChangeTheme -> {
				mTimesChangeTheme++
				if (mTimesChangeTheme % 2 == 0)
					delegate.setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO)
				else
					delegate.setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES)
				Toast.makeText(this, R.string.change_theme, Toast.LENGTH_SHORT).show()
				true
			}
			R.id.menuSaveLastResult -> {
				val sharePreferences: SharedPreferences =
					getSharedPreferences(PREF_RESULT, Context.MODE_PRIVATE)
				val editor: SharedPreferences.Editor = sharePreferences.edit()
				editor.putFloat(PREF_RESULT, takeText().toFloat())
				editor.apply()
				Toast.makeText(this, R.string.result_saved, Toast.LENGTH_SHORT).show()
				true
			}
			else -> super.onOptionsItemSelected(item)
		}
	}

	private fun printLastSavedResult() {
		val sharePreferences: SharedPreferences =
			getSharedPreferences(PREF_RESULT, Context.MODE_PRIVATE)
		if (sharePreferences != null) {
			val lastResult = sharePreferences.getFloat(PREF_RESULT, inputFragment.getResult())
			displayFragment.displayResult(lastResult.toString())
		} else {
			displayFragment.displayResult("")
			Toast.makeText(this, R.string.default_value, Toast.LENGTH_SHORT).show()
		}
	}

	fun displayResult(result: String) {
		displayFragment.displayResult(result)
	}

	fun appendResult(result: String) {
		displayFragment.appendResult(result)
	}

	fun takeText() = displayFragment.takeText()
}
