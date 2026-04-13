import com.example.entity.AuthEntity
import org.hibernate.SessionFactory
import java.util.*
import org.hibernate.cfg.Configuration


object Database {

    private fun loadDatabaseConfig(): Properties {
        val props = Properties()
        val inputStream  = Thread.currentThread()
            .contextClassLoader
            .getResourceAsStream(Params.currentConfig)
            ?: error("database config not found")
        inputStream.use {
            props.load(it)
        }
        return props
    }

    val sessionFactory: SessionFactory by lazy {
        val properties = loadDatabaseConfig()
        Configuration().apply {
            setProperties(properties)
            addAnnotatedClass(AuthEntity::class.java)
        }.buildSessionFactory()
    }

}