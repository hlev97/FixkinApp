package hu.bme.aut.it9p0z.preferences.domain

import hu.bme.aut.it9p0z.model.userinfo.UserInfoModel

data class UserInfo(
    val userName: String,
    val fullName: String,
    val height: Double,
    val weight: Double,
    val diseases: List<String>,
    val medicines: List<String>,
    val averageLifeQualityIndex: Double,
    val password: String
)

fun UserInfo.asUserInfoModel(): UserInfoModel = UserInfoModel(
    userName = userName,
    fullName = fullName,
    height = height,
    weight = weight,
    diseases = diseases,
    medicines = medicines,
    averageLifeQualityIndex = averageLifeQualityIndex,
    password = password
)

fun UserInfoModel.asUserInfo(): UserInfo = UserInfo(
    userName = userName,
    fullName = fullName,
    height = height,
    weight = weight,
    diseases = diseases,
    medicines = medicines,
    averageLifeQualityIndex = averageLifeQualityIndex,
    password = password
)
