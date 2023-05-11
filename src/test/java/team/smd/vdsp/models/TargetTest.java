package team.smd.vdsp.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class TargetTest {

	@Test
	public void compareTargets() {
		Target t1 = new Target("1231", "1");
		Target t2 = new Target("1231", "1");

		assertEquals(t1, t2);
		assertNotEquals(t1, 0);
	}
	
}
