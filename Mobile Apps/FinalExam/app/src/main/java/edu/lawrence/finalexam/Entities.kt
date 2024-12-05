package edu.lawrence.finalexam

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity
data class Guide(@PrimaryKey(autoGenerate = true) val idGuide : Int = 0, val name : String)

@Entity
data class Step(
    @PrimaryKey(autoGenerate = true) val idStep : Int = 0,
    val stepNumber: Int,
    val heading : String,
    val narration : String,
    val guide: Int)

@Dao
interface GuideDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGuide(guide: Guide)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStep(step: Step)

    @Query("SELECT * from guide")
    suspend fun getGuides() : List<Guide>

    @Query("SELECT * from guide where idGuide = :idGuide")
    suspend fun getGuide(idGuide: Int) : Guide

    @Query("SELECT * from guide where name = :name")
    suspend fun getGuide(name: String) : Guide

    @Query("SELECT * from step where guide = :guide ORDER BY stepNumber ASC")
    suspend fun getSteps(guide: Int) : List<Step>
}

@Database(entities = [Guide::class,Step::class], version = 1)
abstract class GuideDatabase : RoomDatabase() {
    abstract fun guideDao() : GuideDao
}