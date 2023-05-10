package team.smd.vdsp.models;

import java.util.Arrays;

public class Step {

	/**
	 * Unified oprand for one step
	 * 
	 * "traverse" | "settle" | "reset"
	 */
	private String type;

	/**
	 * Batch of operating targets
	 * 
	 * for update one or more nodes'state simultaneously
	 */
	private Target[] targets;

	public Step() {
		type = "";
		targets = null;
	}

	public Step(String type, Target[] targets) {
		this.type = type;
		this.targets = Arrays.copyOf(targets, targets.length);
	}

	/**
	 * Override for comparing objects
	 * 
	 * @param otherStep
	 * @return
	 */
	public boolean equals(Step otherStep) {
		if (otherStep.type.equals(this.type)
				&& Arrays.equals(this.getTargets(), otherStep.getTargets())) {
			return true;
		} else {
			return false;
		}
	}

	public void setType(String t) {
		type = t;
	}

	public String getType() {
		return type;
	}

	public void setTargets(Target[] targets) {
		this.targets = targets;
	}

	public Target[] getTargets() {
		return targets;
	}

	public String toString() {
		String result = "Type: " + type + " \n";
		for (int i = 0; i < this.targets.length; i++) {
			result += (this.targets[i] + "\n");
		}
		return result;
	}

	public static void main(String[] args) {
		/* step internal test */

	}

}
