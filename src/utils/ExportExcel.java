package utils;

import com.managesystem.model.PostSalary;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.List;

/**
 * @author xu
 * 数据导出工具类
 */
public class ExportExcel {
	public static void exportData(List<PostSalary> list) {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();//创建一个工作簿对象
		HSSFSheet hssfSheet = hssfWorkbook.createSheet("?????");//创建一个工作表对象
		HSSFRow hssfRow = hssfSheet.createRow(0);
		HSSFCell hssfCell = hssfRow.createCell(0);
		/* 列名 */
		String[] titles = { "姓名", "工号", "职务", "基本工资","变动工资","最后工资" };
		/* for循环生成列名 */
		for (int i = 0; i < titles.length; i++) {
			hssfCell = hssfRow.createCell(i);
			hssfCell.setCellValue(titles[i]);
		}
		/* 填充数据 */
		int rowIndex = 1;
		for (PostSalary postSalary : list) {
			hssfRow = hssfSheet.createRow(rowIndex);
			hssfCell = hssfRow.createCell(0);
			hssfCell.setCellValue(postSalary.getName());
			hssfCell = hssfRow.createCell(1);
			hssfCell.setCellValue(postSalary.getEmployeeid());
			hssfCell = hssfRow.createCell(2);
			hssfCell.setCellValue(postSalary.getPost());
			hssfCell = hssfRow.createCell(3);
			hssfCell.setCellValue(postSalary.getBaseSalary());
			hssfCell = hssfRow.createCell(4);
			hssfCell.setCellValue(postSalary.getBiandongSalary());
			hssfCell = hssfRow.createCell(5);
			hssfCell.setCellValue(postSalary.getEndSalary());
			rowIndex++;
		}
		File file = new File("C:\\Users\\lenovo\\Desktop\\员工工资表.xls");
		OutputStream output = null;
		try {
			output = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			hssfWorkbook.write(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
