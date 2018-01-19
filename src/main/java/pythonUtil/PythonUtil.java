package pythonUtil;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class PythonUtil {
	public static void main(String[] args) {
		PythonInterpreter interpreter = new PythonInterpreter();  
        interpreter.execfile("D:\\wsneon\\pytest1\\src\\helloWorld\\my_utils.py");  
        PyFunction func = (PyFunction)interpreter.get("adder",PyFunction.class);  

        int a = 2010, b = 2 ;
        PyObject pyobj = func.__call__(new PyInteger(a), new PyInteger(b));  
        System.out.println("anwser = " + pyobj.toString());  
        
        
        interpreter.execfile("D:\\wsneon\\pytest1\\src\\helloWorld\\test.py");  
	}
}
