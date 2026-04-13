package repository



import Status
import models.UserModel

interface UserRepository {

    suspend fun createNewUser(userModel: UserModel): Status<UserModel>
    suspend fun deleteUser(userId: Int): Status<Void>
    suspend fun selectUserById(userId: Int): Status<UserModel>
    suspend fun selectUserSliceByRange(userIdRange: IntRange): Status<List<UserModel>>
    suspend fun updateUserById(changedUserModel: UserModel): Status<UserModel>

}

