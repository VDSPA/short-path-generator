package team.smd.vdsp.models;

import org.junit.Test;

public class StepTest {
	@Test
	public void showStep() {
		Target[] t = new Target[2];
		t[0] = new Target("node", "" + 1);
		Step s = new Step("traverse", t);
		System.out.println(s);

		t[1] = new Target("node", "" + 3);
		Step s2 = new Step("traverse", t);
		System.out.println(s2);

	}
	
}
