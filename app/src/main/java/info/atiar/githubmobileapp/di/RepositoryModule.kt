package info.atiar.githubmobileapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import info.atiar.githubmobileapp.user_profile.data.remote.UserProfileApi
import info.atiar.githubmobileapp.user_profile.data.repositoryimpl.UserProfileRepositoryImpl
import info.atiar.githubmobileapp.user_profile.domain.repository.UserProfileRepository
import info.atiar.githubmobileapp.users.data.remote.UsersApi
import info.atiar.githubmobileapp.users.data.repositoryimpl.UserRepositoryImpl
import info.atiar.githubmobileapp.users.domain.repository.UserRepository
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class])
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUsersRepository(usersApi: UsersApi): UserRepository = UserRepositoryImpl(usersApi)

    @Provides
    @Singleton
    fun provideUsersProfileRepository(userProfileApi: UserProfileApi): UserProfileRepository = UserProfileRepositoryImpl(userProfileApi)
}