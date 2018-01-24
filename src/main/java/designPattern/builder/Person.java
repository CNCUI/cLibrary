package designPattern.builder;

public class Person {
	private String title;
	private String body;
	private String ege;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getEge() {
		return ege;
	}
	public void setEge(String ege) {
		this.ege = ege;
	}
	
	public String toString(){
		return "title:"+this.title+",body:"+this.body+",ege:"+this.ege;
	}
}
