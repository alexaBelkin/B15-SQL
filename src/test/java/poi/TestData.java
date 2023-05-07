package poi;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestData {
    XSSFSheet page;
    @Before
    public void setup() throws IOException {
        File excelFile=new File("src/test/resources/TestData (1).xlsx");
        FileInputStream fileInputStream=new FileInputStream(excelFile);
        XSSFWorkbook workbook=new XSSFWorkbook(fileInputStream);
        page= workbook.getSheetAt(0);
    }
    @Test
    public void testData(){
        //retriver info with 100678
        for (int i = page.getFirstRowNum(); i < page.getLastRowNum() ; i++) {
            XSSFRow row= page.getRow(i);
            for (int j= row.getFirstCellNum();j< row.getLastCellNum();j++){
                XSSFCell cell= row.getCell(j);
                String policyNum=String.valueOf(cell);
                if(!policyNum.equals("100678")){
                    continue;
                }
                Assert.assertEquals(String.valueOf(100678),policyNum);
                Assert.assertEquals("WI",row.getCell(3).toString());
                Assert.assertEquals("Midwest",row.getCell(4).toString());
                Assert.assertEquals("Apartment",row.getCell(7).toString());

            }

        }

    }
    @Test
    public void regionValue(){

            int regionColumnIndex=-1;
            XSSFRow FirstRow= page.getRow(0);

                for (int j = FirstRow.getFirstCellNum(); j < FirstRow.getLastCellNum() ; j++) {
                    if(FirstRow.getCell(j).toString().equals("Region")){
                        regionColumnIndex=j;
                    }

                }
        for (int i = page.getFirstRowNum(); i <page.getLastRowNum() ; i++) {
            XSSFRow currentRow=page.getRow(i);
            System.out.println(currentRow.getCell(regionColumnIndex));

        }


    }
}
