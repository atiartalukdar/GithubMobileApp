package info.atiar.githubmobileapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import info.atiar.githubmobileapp.users.data.repositoryimpl.UserRepositoryImpl
import info.atiar.githubmobileapp.users.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideUserRepository(impl: UserRepositoryImpl): UserRepository

}