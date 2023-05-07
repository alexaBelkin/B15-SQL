package poi;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadDataFromExcel {
    @Test
    public void readFileTest() throws IOException {
        File excelfile=new File("src/test/resources/SmokeTestSetup.xlsx");
        FileInputStream fileInputStream=new FileInputStream(excelfile);
        XSSFWorkbook workbook=new XSSFWorkbook(fileInputStream);
        XSSFSheet page1=workbook.getSheetAt(0); //by index
        XSSFRow row1=page1.getRow(0);
        XSSFCell cell1= row1.getCell(0);
        System.out.println(cell1);
    }

    @Test
    public void getRowValuesTest() throws IOException {
        File excelFile=new File("src/test/resources/SmokeTestSetup.xlsx");
        FileInputStream fileInputStream=new FileInputStream(excelFile);
        XSSFWorkbook workbook=new XSSFWorkbook(fileInputStream);
        XSSFSheet page1= workbook.getSheet("Sheet1"); //by sheet name
        XSSFRow row1=page1.getRow(0);
        for (int i = row1.getFirstCellNum(); i <row1.getLastCellNum() ; i++) {
            XSSFCell currentCell= row1.getCell(i);
            System.out.print(currentCell+" |");
        }
    }
    @Test
    public void allRowsValuesTest() throws IOException {
        File excelFile=new File("src/test/resources/SmokeTestSetup.xlsx");
        FileInputStream fileInputStream=new FileInputStream(excelFile);
        XSSFWorkbook workbook=new XSSFWorkbook(fileInputStream);
        XSSFSheet page1=workbook.getSheet("Sheet1");
        for (int i = page1.getFirstRowNum(); i <= page1.getLastRowNum() ; i++) {
            XSSFRow currentRow=page1.getRow(i);
            for (int j =currentRow.getFirstCellNum() ; j < currentRow.getLastCellNum() ; j++) {

                XSSFCell currentCell=currentRow.getCell(j);
                System.out.print(currentCell+" |");

            }
            System.out.println();

        }

    }
}
