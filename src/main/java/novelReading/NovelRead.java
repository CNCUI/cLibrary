package novelReading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class NovelRead {

	/**
	 * @param args
	 * @throws Exception
	 * @throws MalformedURLException
	 */
	public static void main(String[] args) throws MalformedURLException,
			Exception {
		String zjml = "章节目录";
		String xyz = "下一章";
		StringBuilder sb = new StringBuilder();
		String a = null;
		String b = null;
		try {
			File file = new File("novelCurrent.txt");
			File file2 = new File("novelHistory.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			if (!file2.exists()) {
				file2.createNewFile();
			}
			InputStream input = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			String len = null;
			StringBuilder ssb = new StringBuilder();
			while ((len = br.readLine()) != null) {
				ssb.append(len);
			}
			System.out.println("读" + ssb);

			String url = ssb.toString().trim();
			// String url = "http://www.biquge5200.com/79_79875/149689508.html";
			System.out.print("ttttttst111");
			BufferedReader in = new BufferedReader(new InputStreamReader(new URL(url).openConnection().getInputStream(), "GB2312"));// GB2312可以根据需要替换成要读取网页的编码
			System.out.print("ttttttst222");
			
			while ((a = in.readLine()) != null) {
				if (a.indexOf("j_chapterNext") != -1) {
					// b =
					// "https:"+a.substring(a.indexOf("href")+6,a.indexOf("data-eid")-2);
					b = a;
				}
				sb.append(a);
			}
			System.out.println(sb.substring(sb.indexOf("<div id=\"content\">"),
					sb.indexOf("bdshare()")));
			// System.out.println(sb);
			// System.out.println(a);

			// byte[] zjmyiso = zjml.getBytes("IOS-8859-1");
			// byte[] xyziso = xyz.getBytes("IOS-8859-1");
			// System.out.println(new String(zjmyiso,"UFT-8"));
			// System.out.println(new String(xyziso,"UFT-8"));
			System.out.println();
			a = sb.substring(sb.indexOf("章节目录") + 20, sb.indexOf("下一章"));

			System.out.println("xiexieixieixieixieixie");
			System.out.println("write" + a);
			a = a.substring(a.indexOf("\"") + 1, a.lastIndexOf("\""));
			System.out.println("write" + a);
			FileOutputStream o = new FileOutputStream(file);
			FileOutputStream o2 = new FileOutputStream(file2, true);
			o.write(a.toString().getBytes());
			o2.write((a + "\n").toString().getBytes());
			o.close();
			in.close();
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		} finally {
		}
	}

}
