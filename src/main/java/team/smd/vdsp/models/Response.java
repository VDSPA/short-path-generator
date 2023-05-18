package team.smd.vdsp.models;

public class Response<T> {
	private T data;
	private String msg;
	private int code;

	public Response(T data, String msg, int code) {
		this.data = data;
		this.msg = msg;
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
