package team.smd.vdsp.models;

import java.util.LinkedList;

public class Result {
	String name;
	LinkedList<Step> steps;

	public Result(String name, LinkedList<Step> result) {
		this.name = name;
		this.steps = result;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedList<Step> getSteps() {
		return steps;
	}

	public void setSteps(LinkedList<Step> steps) {
		this.steps = steps;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Result other = (Result) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (steps == null) {
			if (other.steps != null)
				return false;
		} else if (!steps.equals(other.steps))
			return false;
		return true;
	}

}
