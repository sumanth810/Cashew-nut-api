package in.koyya.ays.rsp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@SpringBootApplication
public class RSPApplicationTests {

	@Test
	public void shouldWork() {
		assertEquals(10, 5+5);
	}
}
