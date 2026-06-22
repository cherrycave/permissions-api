import dev.boecker.cherrycave.permission.luckperms.LuckpermsRest
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import java.util.UUID
import kotlin.test.Test

internal class UserTests {
    lateinit var luckpermsRest: LuckpermsRest

    @BeforeEach
    fun setup() {
        luckpermsRest = LuckpermsRest("http://localhost:25401")
    }

    @Test
    fun getUserData(): Unit = runBlocking {
        val userData = luckpermsRest.user.getUser(UUID.fromString("b9496e29-0b76-4da9-ba6d-85992b1e6f86"))
        println(userData)
    }
}