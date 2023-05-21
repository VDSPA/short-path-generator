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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Target other = (Target) obj;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
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

	public String toString() {
		return "Role: " + role + " " + "Id: " + id + " ";
	}

}
