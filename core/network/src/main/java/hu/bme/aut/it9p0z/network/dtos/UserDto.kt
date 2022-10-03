package hu.bme.aut.it9p0z.network.dtos

import hu.bme.aut.it9p0z.model.userinfo.UserInfoModel

data class UserDto(
    val userName: String,
    val fullName: String?,
    val height: Double?,
    val weight: Double?,
    val diseases: ArrayList<String> = arrayListOf(),
    val medicines: ArrayList<String> = arrayListOf(),
    val averageLifeQualityIndex: Double?,
    val password: String,
)

fun UserDto.asUserInfoModel() = UserInfoModel(
    userName = userName,
    fullName = fullName,
    height = height,
    weight = weight,
    diseases = diseases,
    medicines = medicines,
    averageLifeQualityIndex = averageLifeQualityIndex,
    password = password
)

fun UserInfoModel.asUserDto() = UserDto(
    userName = userName,
    fullName = fullName,
    height = height,
    weight = weight,
    diseases = diseases,
    medicines = medicines,
    averageLifeQualityIndex = averageLifeQualityIndex,
    password = password
)