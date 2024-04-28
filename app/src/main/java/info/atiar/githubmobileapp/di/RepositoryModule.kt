package info.atiar.githubmobileapp.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import info.atiar.githubmobileapp.users.data.remote.UsersApi
import info.atiar.githubmobileapp.users.data.repositoryimpl.UserRepositoryImpl
import info.atiar.githubmobileapp.users.domain.repository.UserRepository
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class])
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(usersApi: UsersApi): UserRepository = UserRepositoryImpl(usersApi)
}