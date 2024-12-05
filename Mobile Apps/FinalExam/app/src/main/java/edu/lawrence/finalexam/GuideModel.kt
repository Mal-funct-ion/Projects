package edu.lawrence.finalexam

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GuideModel(application: Application): AndroidViewModel(application) {
    lateinit var dao: GuideDao
    val guides = mutableStateOf<List<Guide>?>(null)
    val steps = mutableStateOf<List<Step>?>(null)
    val selectedGuide = mutableStateOf<Guide?>(null)

    init {
        val db = Room.databaseBuilder(
            application,
            GuideDatabase::class.java, "guide"
        ).build()
        dao = db.guideDao()

        viewModelScope.launch {
            var guideList: List<Guide>
            withContext(Dispatchers.IO) {
                guideList = dao.getGuides()
            }
            withContext(Dispatchers.Main) {
                guides.value = guideList
            }
        }
    }

    fun addGuide(guide: Guide){
        viewModelScope.launch {
            var newGuide: Guide
            withContext(Dispatchers.IO) {
                dao.insertGuide(guide)
                newGuide = dao.getGuide(guide.name)
            }
            withContext(Dispatchers.Main) {
                getGuide()
                selectedGuide.value = newGuide
            }
        }
    }

    fun getGuide(){
        viewModelScope.launch {
            var guideList: List<Guide>
            withContext(Dispatchers.IO) {
                guideList = dao.getGuides()
            }
            withContext(Dispatchers.Main) {
                guides.value = guideList
            }
        }
    }

    fun addStep(step: Step){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dao.insertStep(step)
            }
            withContext(Dispatchers.Main) {
                getSteps(step.guide)
            }
        }
    }

    fun getSteps(guide: Int){
        viewModelScope.launch {
            var stepList: List<Step>
            var curGuide: Guide
            withContext(Dispatchers.IO) {
                stepList = dao.getSteps(guide)
                curGuide = dao.getGuide(guide)
            }
            withContext(Dispatchers.Main) {
                steps.value = stepList
                selectedGuide.value = curGuide
            }
        }
    }
}