package info.atiar.githubmobileapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Named("package_name")
    fun providesPackageTitle(): String {
        return "Github Demo"
    }

    @Provides
    @Named("main_dispatcher")
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Named("io_dispatcher")
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}