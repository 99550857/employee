package utils;

import com.managesystem.model.*;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;

import java.io.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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


    /**
     * 读取Excel数据内容
     * @param is
     * @return 一组学生数据list集合
     */
    public List<EmployeeInfo> readExcelContent(InputStream is) {
        List<EmployeeInfo> studentList = new ArrayList<>();
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //得到第一张工作表
        sheet = wb.getSheetAt(0);
        // 正文内容应该从第二行开始,第一行为表头的标题
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //所有要导入的学生对象使用这个默认头像
        File file = new File("src/img/default.jpg");
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] b = new byte[(int) file.length()];
        try {
            inputStream.read(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //循环读取表格每一行，封装成一个Student对象，然后放入list中
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            String employeeid = row.getCell(0).getRichStringCellValue().getString().trim();
            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            String departmentid = row.getCell(1).getRichStringCellValue().getString().trim();
            row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
            String name = row.getCell(2).getRichStringCellValue().getString().trim();
            row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
            String gender = row.getCell(3).getRichStringCellValue().getString().trim();
            row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
            String nation = row.getCell(4).getRichStringCellValue().getString().trim();
            row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
            String province = row.getCell(5).getRichStringCellValue().getString().trim();
            row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
            String municipality = row.getCell(6).getRichStringCellValue().getString().trim();
            row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
            String address = row.getCell(7).getRichStringCellValue().getString().trim();
            row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
            String eduback = row.getCell(8).getRichStringCellValue().getString().trim();
            row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
            String post = row.getCell(9).getRichStringCellValue().getString().trim();
            row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
            String tectitle = row.getCell(10).getRichStringCellValue().getString().trim();
            row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
            String marital_status = row.getCell(11).getRichStringCellValue().getString().trim();
            row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
            String political_status = row.getCell(12).getRichStringCellValue().getString().trim();
            row.getCell(13).setCellType(Cell.CELL_TYPE_STRING);
            String entry_time = row.getCell(13).getRichStringCellValue().getString().trim();
            row.getCell(14).setCellType(Cell.CELL_TYPE_STRING);
            String phone = row.getCell(14).getRichStringCellValue().getString().trim();
            row.getCell(15).setCellType(Cell.CELL_TYPE_STRING);
            String email = row.getCell(15).getRichStringCellValue().getString().trim();
            java.util.Date date = null;
            try {
                date = sdf.parse(entry_time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date sqlDate = new Date(date.getTime());
            EmployeeInfo employeeInfo = new EmployeeInfo();
            employeeInfo.setEmployeeid(employeeid);
            employeeInfo.setDepartmentid(departmentid);
            employeeInfo.setName(name);
            employeeInfo.setGender(gender);
            employeeInfo.setNation(nation);
            employeeInfo.setProvince(province);
            employeeInfo.setMunicipality(municipality);
            employeeInfo.setAddress(address);
            employeeInfo.setEduback(eduback);
            employeeInfo.setPost(post);
            employeeInfo.setTectitle(tectitle);
            employeeInfo.setMarital_status(marital_status);
            employeeInfo.setPolitical_status(political_status);
            employeeInfo.setEntry_time(sqlDate);
            employeeInfo.setPhone(phone);
            employeeInfo.setEmail(email);
            employeeInfo.setAvatar(null);
            studentList.add(employeeInfo);
        }
        return studentList;
    }

    public static void main(String[] args) throws Exception {
        File file = new File("f:\\employee.xls");
        InputStream is = new FileInputStream(file);
        ExcelUtil excelUtil = new ExcelUtil();
        List<EmployeeInfo> List = excelUtil.readExcelContent(is);
        List.forEach(employeeInfo -> System.out.println(employeeInfo));
    }
}

