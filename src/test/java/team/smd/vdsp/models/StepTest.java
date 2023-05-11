package team.smd.vdsp.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class StepTest {

	@Test
	public void compareSteps() {
		Target[] t1 = new Target[1];
		t1[0] = new Target("node", "1");
		Step s1 = new Step("traverse", t1);

		Target[] t2 = new Target[1];
		t2[0] = new Target("node", "1");
		Step s2 = new Step("traverse", t2);

		assertEquals(s1, s2);
		assertNotEquals(s1, 0);
	}
	
}
