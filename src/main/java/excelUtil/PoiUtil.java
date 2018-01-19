package excelUtil;

import httl.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import check.CommonUtil;


/**
 * 导入文件工具类
 */
public class PoiUtil {
	private static Logger logger = LoggerFactory.getLogger(PoiUtil.class);
	/**
	 * excel文件类型
	 */
	public final static String EXCELTYPE = "XLS,XLSX,";
	/**
	 * CSV文件类型
	 */
	public final static String CSVTYPE = "CSV,";
	// 主键title高亮颜色
	public final static short TITLE_HIGH_LIGHT_COLOR = HSSFColor.YELLOW.index;

	/**
	 * 是否excel
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isExcel(String fileName) {
		boolean flag = false;
		if (!CommonUtil.isEmpty(fileName)) {
			// 判断文件类型
			flag = EXCELTYPE.contains(getFileSuffix(fileName) + ",");
		}
		return flag;
	}

	/**
	 * 文件后缀
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileSuffix(String fileName) {
		// 判断文件类型
		int index = fileName.lastIndexOf(".");
		String suffix = fileName.substring(index + 1).toUpperCase();
		return suffix;
	}

	/**
	 * 是否03
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean isExcel2003(String fileName) {
		return fileName.matches("^.+\\.(?i)(xls)$");
	}

	/**
	 * @描述：是否是2007的excel，返回true是2007
	 * @参数：@param fileName
	 * @参数：@return
	 * @返回值：boolean
	 */
	public static boolean isExcel2007(String fileName) {
		return fileName.matches("^.+\\.(?i)(xlsx)$");
	}

	/**
	 * @描述：是否是CSV文件
	 */
	public static boolean isCsv(String fileName) {
		return fileName.matches("^.+\\.(?i)(csv)$");
	}

	/**
	 * 得到Excel表中的值
	 * 
	 * @param Cell
	 *            Excel中的每一个格子
	 * @return Excel中每一个格子中的值
	 */
	private static DecimalFormat df = new DecimalFormat("#");

	public static String getCellValue(Cell cell) {
		try {
			if (null == cell) {
				return "";
			}
			if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
				// 返回布尔类型的值
				return String.valueOf(cell.getBooleanCellValue());
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					Date d = cell.getDateCellValue();
					return DateUtils.getDateTimeString(d);
				} else {
					// 返回数值类型的值
					return String
							.valueOf(df.format(cell.getNumericCellValue()));
				}
			} else {
				// 返回字符串类型的值
				return String.valueOf(cell.getStringCellValue());
			}
		} catch (Exception e) {
			return "";
		}

	}

	/**
	 * 获取excel的workbook
	 * 
	 * @param is
	 *            :文件流
	 * @param fileName
	 *            :文件名称
	 * @return
	 */
	public static Workbook getWorkBook(InputStream is, String fileName) {
		Workbook workbook = null;
		try {
			if (isExcel2003(fileName)) {
				try {
					POIFSFileSystem fs = new POIFSFileSystem(is);
					workbook = new HSSFWorkbook(fs);
				} catch (OfficeXmlFileException e) {
					workbook = new XSSFWorkbook(is);
				}
			} else if (isExcel2007(fileName)) {
				workbook = new XSSFWorkbook(is);
			}
		} catch (Exception e) {
			logger.error("获取工作簿错误："+e.getMessage());
		}
		return workbook;
	}

	/**
	 * 获取模版的表头，避免模版出现变动，此步骤用于判断是否有多余列校验步骤如下： 1.首先获取模版头部的列信息，存入map并以下标为map的key
	 * 2.在遍历内容的时候continue掉 3.拼接成最后需要的sql语句
	 * 案例可参考PcsTrackingImpAction.getImpList方法
	 * 
	 * @param row
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map getTempHeaders(Row row) {
		// 要返回的map,key列的下标，value是内容如果内容为空值那么忽略改列
		Map resultMap = new HashMap();
		for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
			// 获取列对象
			Cell cell = row.getCell(cellNum);
			// 获取列值
			String content = PoiUtil.getCellValue(cell);
			resultMap.put(cellNum, content.trim());
		}
		return resultMap;

	}

	/**
	 * 远程url文件下载，并获取该文件
	 * 
	 * @param filePath
	 *            :
	 * @param filepath
	 * @return
	 * @throws Exception
	 */
	public static File getDownLoadFile(String remoteUrl, String localPath)
			throws Exception {
		URL url = new URL(remoteUrl);
		// 打开连接
		URLConnection con = url.openConnection();
		// 输入流
		InputStream is = con.getInputStream();
		// 文件名
		int fileNameIndex = remoteUrl.lastIndexOf("\\") == -1 ? remoteUrl
				.lastIndexOf("/") : remoteUrl.lastIndexOf("\\");
		String filename = remoteUrl.substring(fileNameIndex + 1);
		return writeFile(localPath, filename, is, false);
	}

	/**
	 * 创建文件
	 * 
	 * @param localPath
	 *            :保存到的本地目录
	 * @param filename
	 *            :保存的名字
	 * @param is
	 *            :输入流
	 * @param deleteOnExit
	 *            :是否删除已经存在的
	 * @return
	 * @throws Exception
	 */
	public static File writeFile(String localPath, String filename,
			InputStream is, boolean deleteOnExit) throws Exception {
		// 目录是否存在
		String absPath = "";
		File filepath = new File(absPath);
		if (!filepath.exists()) {
			filepath.mkdirs();
		}
		File downFile = new File(absPath + File.separator + filename);
		// 删除已经存在的
		if (deleteOnExit) {
			if (downFile.exists()) {
				downFile.delete();
			}
		}
		if (!downFile.exists()) {

			// 1K的数据缓冲
			byte[] bs = new byte[1024 * 1024];
			// 读取到的数据长度
			int len;
			// 输出的文件流
			OutputStream os = new FileOutputStream(downFile);
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			// 完毕，关闭所有链接
			os.close();
			is.close();
		}
		return downFile;
	}

	/**
	 * 删除一个已经存在的文件
	 * 
	 * @param localPath
	 *            :文件路径
	 * @param filename
	 *            ：文件名
	 */
	public static void deleteOnExists(String localPath, String filename) {
		// 目录是否存在
		String absPath = "";
		// 全路径
		String filePath = absPath + File.separator + filename;
		deleteFile(filePath);
	}

	/**
	 * 删除一个已经存在的文件
	 * 
	 * @param filePath
	 *            ：文件全路径
	 */
	public static void deleteFile(String filePath) {
		File existsFile = new File(filePath);
		if (existsFile.exists()) {
			existsFile.delete();
		}
	}

	/**
	 * 通过POI创建Excel2003工作薄
	 * 
	 * @param title
	 *            数据标题
	 * @param hlColumnName
	 *            高亮列名
	 * @param rows
	 *            数据
	 * @return
	 */
	public static HSSFWorkbook CreateExcel2003ByPOI(String[] title,
			String hlColumnName, List<Map> rows) {
		// 创建excel工作薄
		HSSFWorkbook workBook = new HSSFWorkbook();
		// 创建一个工作表单
		HSSFSheet sheet = workBook.createSheet();
		// 设置单元格样式
		HSSFCellStyle cellStyle = setCommonCellStyle(workBook);

		// excel行
		HSSFRow row = null;
		// 每个单元格
		HSSFCell cell = null;
		// 列数
		int col_count = title.length;
		// 数据行数
		int row_count = rows.size();

		/**
		 * 设置标题行
		 */
		// 创建行
		row = sheet.createRow(0);
		// 在第一行写入标题
		for (int i = 0; i < col_count; i++) {
			// 创建一个字符串格式的单元格
			cell = row.createCell(i, HSSFCell.CELL_TYPE_STRING);

			if (!StringUtils.isEmpty(hlColumnName)
					&& hlColumnName.equals(title[i])) {
				cell.setCellStyle(setHlCellStyle(workBook,
						TITLE_HIGH_LIGHT_COLOR));
			} else {
				cell.setCellStyle(cellStyle);
			}
			cell.setCellValue(new HSSFRichTextString(title[i]));
			// logger.debug("title[" + i + "] = " + title[i]);
			sheet.autoSizeColumn(i);
		}

		/**
		 * 写入各行数据
		 */
		// 第二行开始写入数据
		for (int i = 1; i <= row_count; i++) {
			row = sheet.createRow(i);
			Map rowData = rows.get(i - 1);
			// logger.debug("-----------------第" + i +
			// "行数据---------------------");

			for (int j = 0; j < col_count; j++) {
				// 创建一个字符串格式的单元格
				cell = row.createCell(j, HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(cellStyle);
				// logger.debug("第" + j + "列" + "   title = " + title[j] +
				// "    value = " + convertValue2Str(rowData.get(title[j])));
				cell.setCellValue(new HSSFRichTextString(
						convertValue2Str(rowData.get(title[j]))));
				// sheet.autoSizeColumn(j);
			}
		}

		return workBook;
	}

	// public static void main(String args[]) throws Exception
	// {
	// String[] title = {"col1", "col2", "col3"};
	// HSSFWorkbook book = CreateExcel2003ByPOI(title, new ArrayList());
	// write2Local(book);
	// }

	// private static void write2Local(HSSFWorkbook workBook) throws Exception
	// {
	// // 输出的文件流
	// OutputStream os = new FileOutputStream(new File("d:/test.xls"));
	// workBook.write(os);
	// os.close();
	// }

	/**
	 * 设置通用单元格样式
	 * 
	 * @param workBook
	 *            excel工作薄对象
	 * @return 通用excel单元格样式
	 */
	private static HSSFCellStyle setCommonCellStyle(HSSFWorkbook workBook) {
		HSSFCellStyle cellStyle = workBook.createCellStyle();
		/**
		 * 设置字体样式
		 */
		HSSFFont font = workBook.createFont();
		// 普通字体
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 10号字
		font.setFontHeightInPoints((short) 10);
		// 设置标题字体
		font.setFontName("宋体");
		cellStyle.setFont(font);

		return cellStyle;
	}

	/**
	 * 设置高亮单元格样式
	 * 
	 * @param workBook
	 *            excel工作薄对象
	 * @param 背景色
	 *            取自HSSFColor中
	 * @return 通用excel单元格样式
	 */
	private static HSSFCellStyle setHlCellStyle(HSSFWorkbook workBook,
			short cellColor) {
		HSSFCellStyle cellStyle = workBook.createCellStyle();
		/**
		 * 设置字体样式
		 */
		HSSFFont font = workBook.createFont();
		// 普通字体
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 10号字
		font.setFontHeightInPoints((short) 10);
		// 设置标题字体
		font.setFontName("宋体");
		cellStyle.setFont(font);
		// 设置填充样式为使用前景色
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		// 设置前景色
		cellStyle.setFillForegroundColor(cellColor);

		return cellStyle;
	}

	/**
	 * 设置单元格前景色
	 * 
	 * @param cell
	 *            需要设置的cell对象
	 * @param cellColor
	 */
	private static void setCellFG(HSSFCell cell, short cellColor) {
		if (cell == null) {
			return;
		}

		// 获取cell样式对象
		cell.getCellStyle().cloneStyleFrom(cell.getCellStyle());
		HSSFCellStyle cellStyle = cell.getCellStyle();

		cell.setCellStyle(cellStyle);
	}

	/**
	 * 值类型转换 通过判断传入值的类型，转换成String类型
	 * 
	 * @param value
	 * @return 转换后的String
	 */
	public static String convertValue2Str(Object value) {
		String returnVal = null;
		if (value == null)
			value = "";

		// Timestamp类型处理
		if (value.getClass().getName().equals("java.sql.Timestamp")) {
			returnVal = DateUtils.timestampToString((java.sql.Timestamp) value);
		}
		// 整形变量处理
		else if (value.getClass().getName().equals("java.lang.Integer")) {
			returnVal = String.valueOf(((Integer) value).intValue());
		} else {
			returnVal = String.valueOf(value);
		}

		return returnVal;
	}

}
