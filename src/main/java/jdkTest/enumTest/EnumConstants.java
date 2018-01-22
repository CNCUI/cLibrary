package jdkTest.enumTest;

public enum EnumConstants {
	START(0,"0","初始"),
	ACCEPT(1,"1","接收"),
	REFUSE(2,"2","拒绝");
	int num;
	String value;
	String descri;
	EnumConstants(int n,String val,String des){
		num = n;
		value = val;
		descri = des;
	}
	public String getValue(){
		return value;
	}

}
