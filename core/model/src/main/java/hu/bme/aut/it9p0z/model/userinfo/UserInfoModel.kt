package hu.bme.aut.it9p0z.model.userinfo

data class UserInfoModel(
    val userName: String,
    val fullName: String?,
    val height: Double?,
    val weight: Double?,
    val diseases: ArrayList<String> = arrayListOf(),
    val medicines: ArrayList<String> = arrayListOf(),
    val averageLifeQualityIndex: Double?,
    val password: String,
)