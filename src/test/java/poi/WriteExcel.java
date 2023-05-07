package poi;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;

public class WriteExcel {

    @Test
    public void writeTest() throws IOException {
        XSSFWorkbook workbook=new XSSFWorkbook(new FileInputStream(new File("src/test/resources/SmokeTestSetup.xlsx")));
        XSSFSheet page1= workbook.getSheetAt(0);
        XSSFRow row= page1.getRow(0);
        XSSFCell newCell = row.createCell(6);
        newCell.setCellValue("Owner");
        FileOutputStream fos=new FileOutputStream(new File("src/test/resources/SmokeTestSetup.xlsx"));
        workbook.write(fos);

    }
}
