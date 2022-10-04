package hu.bme.aut.it9p0z.model.userinfo

data class UserInfoModel(
    val userName: String,
    val fullName: String,
    val height: Double,
    val weight: Double,
    val diseases: List<String> = mutableListOf(),
    val medicines: List<String> = mutableListOf(),
    val averageLifeQualityIndex: Double,
    val password: String,
)