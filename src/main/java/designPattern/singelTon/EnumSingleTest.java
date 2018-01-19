package designPattern.singelTon;

public class EnumSingleTest {
	public static void main(String[] args) {
		T t1 =EnumSingleton.INSTANCE.getInstance();
		T t2 =EnumSingleton.INSTANCE.getInstance();
		System.out.println(t1 == t2);
		
		System.out.println(SeasonsEnum.SPRING.getEnDescription());
		
		
	}
}

enum SeasonsEnum{
    /**0-春天 */  
    SPRING((short)0, "春天", "Spring"),  
    /**1-夏天 */  
    SUMMER((short)1, "夏天", "Summer"),  
    /**2-秋天 */  
    AUTUMN((short)2, "秋天", "Autumn"),  
    /**3-冬天 */  
    WINTER((short)3, "冬天", "Winter");  
      
    /**枚举值 */  
    private short value;  
    /**中文名称 */  
    private String chDescription;  
    /**英文名称 */  
    private String enDescription;  
      
    SeasonsEnum(short value, String chDescription, String enDescription){  
        this.value = value;  
        this.chDescription = chDescription;  
        this.enDescription = enDescription;  
    }  
  
    public short getValue() {  
        return value;  
    }  
  
    public String getChDescription() {  
        return chDescription;  
    }  
  
    public String getEnDescription() {  
        return enDescription;  
    }
      
   
}  