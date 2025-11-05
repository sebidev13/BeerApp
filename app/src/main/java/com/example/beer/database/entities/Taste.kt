package com.example.beer.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


enum class Sweetness(val description: String) {
    DRY("Dry"),
    BALANCED("Balanced"),
    SWEET("Sweet"),
    VERY_SWEET("Very sweet");

    override fun toString(): String = description
}

enum class Bitterness(val description: String) {
    HARDLY_BITTER("Hardly bitter"),
    LIGHTLY_BITTER("Lightly bitter"),
    NOTICEABLY_BITTER("Noticeably bitter"),
    STRONGLY_BITTER("Strongly bitter");

    override fun toString(): String = description
}

enum class Mouthfeel(val description: String) {
    THIN("Thin"),
    MEDIUM("Medium"),
    FULL_BODIED("Full-bodied"),
    HEAVY("Heavy");

    override fun toString(): String = description
}

enum class Aftertaste(val description: String) {
    SHORT("Short"),
    SMOOTH("Smooth"),
    SPICY("Spicy"),
    LONG_LASTING("Long-lasting");

    override fun toString(): String = description
}

@Entity(tableName = "tastes")
data class Taste(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val sweetness: Sweetness?,
    val bitterness: Bitterness?,
    val mouthfeel: Mouthfeel?,
    val aftertaste: Aftertaste?,
    val note: String?
)