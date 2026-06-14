//package com.example.testingditask
//
//import android.content.Context
//import dagger.Binds
//import dagger.BindsInstance
//import dagger.Component
//import dagger.Module
//import dagger.Provides
//import javax.inject.Inject
//import javax.inject.Provider
//import javax.inject.Qualifier
//import kotlin.random.Random
//
//interface Logger {
//    fun log(message: String)
//}
//
//class ConsoleLogger @Inject constructor() : Logger {
//    override fun log(message: String) {
//        println("ConsoleLogger: $message")
//    }
//
//}
//
//class FileLogger @Inject constructor() : Logger {
//    override fun log(message: String) {
//        println("FileLogger: $message")
//    }
//
//}
//
//
//@Module(includes = [LoggerModule.LoggerBindModule::class])
//class LoggerModule() {
//    @Module
//    interface LoggerBindModule {
//
//        @Binds
//        @ConsoleLoggerQualifier
//        fun bindConsoleLogger(consoleLogger: ConsoleLogger): Logger
//
//
//        @Binds
//        @FileLoggerQualifier
//        fun bindFileLogger(fileLogger: FileLogger): Logger
//
//    }
//
//}
//
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class ConsoleLoggerQualifier
//
//
//@Qualifier
//@Retention(AnnotationRetention.BINARY)
//annotation class FileLoggerQualifier
//
//
//class AnalyticsTracker @Inject constructor(
//    @ConsoleLoggerQualifier
//    private val consoleLogger: Logger,
//    @FileLoggerQualifier
//    private val fileLogger: Logger
//) {
//    fun initLogger() {
//        consoleLogger.log("Console logger initialized")
//        fileLogger.log("File logger initialized")
//    }
//}
//
//class IdGenerator @Inject constructor() {
//    val id = Random.nextInt()
//}
//
//class OrderManager @Inject constructor(private val idGenerator: Provider<IdGenerator>) {
//    fun createOrder() = println("Order ID: ${idGenerator.get().id}")
//}
//
//interface AuthRepository
//class AuthRepositoryImpl @Inject constructor() : AuthRepository
//
//@Module
//interface LegacyModule {
//
//
//    @Binds
//    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository
//
//
//}
//
////@Component(modules =[LegacyModule::class])
////interface AppComponent {
////    fun inject(activity: MainActivity)
////
////    @Component.Factory
////    interface Factory{
////        fun create(@BindsInstance context: Context): AppComponent
////    }
////
////}