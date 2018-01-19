package webservice;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService 
public class ServiceT1 {
	public String transWords(String words){  
        String res="";  
        for(char ch : words.toCharArray()){  
            res+="\t"+ch+"\t";  
        } 
        System.out.println(res);
        return res;  
    }  
  
    //这里我们使用main方法来发布我们的service  
    public static void main(String[] args){  
        Endpoint.publish("http://localhost:9001/Service/Function",new ServiceT1());  
        System.out.println("Publish Success~");  
    }  
}
