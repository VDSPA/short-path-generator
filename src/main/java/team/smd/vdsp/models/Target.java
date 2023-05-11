package team.smd.vdsp.models;

public class Target {

	/** 
	 * Action target identifier
	 * 
	 * "node" or "edge"
	 */
	private String role;

	/** 
	 * id
	 * 
	 * A number like string for node `1`
	 * A pair like string for edge `1:2`
	 */
	private String id;

	public Target() {
		role = "";
		id = "";
	}

	public Target(String role, String id) {
		this.role = role;
		this.id = id;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Target target = (Target) obj;
		if ((target.id.equals(this.id)) && (target.role.equals(this.role))) {
			return true;
		} else {
			return false;
		}
	}

	public void setRole(String r) {
		role = r;
	}

	public void setId(String i) {
		id = i;
	}

	public String getRole() {
		return role;
	}

	public String getId() {
		return id;
	}

	public void setTarget(String r, String i) {
		role = r;
		id = i;
	}

	public String toString () {
		return "Role: " + role + " " + "Id: " + id + " ";
	}
}
