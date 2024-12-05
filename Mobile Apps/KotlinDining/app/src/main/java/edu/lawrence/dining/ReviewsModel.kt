package edu.lawrence.dining

import android.app.Application
import android.content.Context.MODE_PRIVATE
import androidx.lifecycle.AndroidViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class   ReviewsModel(application: Application): AndroidViewModel(application) {
    lateinit var reviews : List<Review>
    private val context = application

    init {
        try {
            val json = context.openFileInput("reviews.json").bufferedReader().use { it.readText() }
            val sType = object : TypeToken<List<Review>>() { }.type
            reviews = Gson().fromJson<List<Review>>(json, sType)
        } catch (e: IOException) {
            reviews = listOf<Review>()
        }
    }

    private fun saveChanges() {
        val gson = Gson()
        val json : String = gson.toJson(reviews)
        try {
            context.openFileOutput("reviews.json", MODE_PRIVATE).use { fos ->
                fos.write(json.toByteArray())
            }
        } catch( e : IOException) {
            e.printStackTrace()
        }
    }

    fun createReview(food : String,rating : Int) {
        val timenow = Calendar.getInstance().time
        val curDate = SimpleDateFormat("MMMM dd, yyyy", Locale.US).format(timenow)
        val newReview = Review(food = food,rating = rating,date = curDate)
        reviews = reviews + newReview
        saveChanges()
    }

}