package utils;

import com.managesystem.model.PostSalary;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xu
 * 操作Excel表格的功能类
 */
public class ExcelUtil {
    //excel文件对象
    private POIFSFileSystem fs;
    // 工作簿对象
    private HSSFWorkbook wb;
    // 工作表对象
    private HSSFSheet sheet;
    // 行对象
    private HSSFRow row;
    private PostSalary student;


    /**
     * 读取Excel数据内容
     * @param is
     * @return 一组salary数据list集合
     */
    public List<PostSalary> readExcelContent(InputStream is) {
        List<PostSalary> salaryList = new ArrayList<>();
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //得到第一张工作表
        sheet = wb.getSheetAt(0);
        InputStream inputStream = null;
        //循环读取表格每一行，封装成一个List对象，然后放入list中
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            String name = row.getCell(0).getRichStringCellValue().getString().trim();
            String post = row.getCell(1).getRichStringCellValue().getString().trim();
            String Id = row.getCell(2).getRichStringCellValue().getString().trim();
            String basesalary = row.getCell(3).getRichStringCellValue().getString().trim();
            String biandong= row.getCell(4).getRichStringCellValue().getString().trim();
            String endsalary = row.getCell(5).getRichStringCellValue().getString().trim();

            PostSalary salary=new PostSalary();
            salary.setName(name);
            salary.setPost(post);
            salary.setEmployeeid(Id);
            salary.setBaseSalary(Integer.parseInt(basesalary));
            salary.setBiandongSalary(Integer.parseInt(biandong));
            salary.setEndSalary(Integer.parseInt(endsalary));

            salaryList.add(salary);
        }
        return salaryList;
    }

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\lenovo\\Desktop\\员工工资表.xls");
        InputStream is = new FileInputStream(file);
        ExcelUtil excelUtil = new ExcelUtil();
        List<PostSalary> postSalaryList = excelUtil.readExcelContent(is);
        postSalaryList.forEach(student -> System.out.println(student));
    }
}

