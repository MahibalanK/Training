package TestCode;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class UrlCheck {
    public static WebDriver driver;


    public static void main(String args[]) throws IOException, InterruptedException {
        String fileLocation = "C:/Users/CC326628/Installs/TRY/Dotcoza/SiteMapUrlCheck.xlsx";
        FileInputStream file = new FileInputStream(new File(fileLocation));
        Workbook workbook = new XSSFWorkbook(file);

        Sheet sheet = workbook.getSheetAt(1);

        LinkedList<String> UrlBundle = new LinkedList<String>();
        LinkedList<String> ErrorLinks = new LinkedList<String>();
        int i = 0;
        for (Row row : sheet) {
            for (Cell mycell : row) {

                if(!(mycell.getColumnIndex() ==1)){
                    i=i+1;
                    System.out.println("total links  "+i);
                    System.out.println("cell value is "+mycell.getStringCellValue());
                    UrlBundle.add(mycell.getStringCellValue());
                }

            }
        }
        UrlBundle.removeAll(Arrays.asList("", null));
        System.out.println("length of Urls stored "+UrlBundle.size());
        int iterationNo = 0;
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().to("https://stg-personal.nedbank.co.za/sitemap.xmlele");
        Thread.sleep(6000);
        for (String ele : UrlBundle) {
            try {
                driver.navigate().to(ele);
                if(ele.endsWith("winter-campaign.html")){
                    Thread.sleep(3000);
                }
                String pageSource = driver.getPageSource();
                if(pageSource.contains("not found: No resource found") || pageSource.contains("not found on this server")
                        || pageSource.contains("404 error!") || pageSource.contains("This page is not available")){
                    System.out.println("ERROR LINK ------- "+ele);
                    ErrorLinks.add(ele);
                }
            }
            catch (Exception exception){
                ErrorLinks.add(ele);
                System.out.println("ISSUE IN NAVIGATION URL ----"+ele);
            }

        }
        driver.quit();
        for (int j = 0; j < UrlBundle.size(); j++) {
            writeExcelWithStatus("PASS",j,1);
        }

        for(String errorLnk:ErrorLinks) {
            iterationNo = UrlBundle.indexOf(errorLnk);
            System.out.println("Error link storage "+ errorLnk);
            System.out.println("Row Num "+ iterationNo);
            writeExcelWithStatus("FAIL",iterationNo,1);
        }


    }


        static void writeExcelWithStatus(String Status,int ParamRow,int ParamCol) throws IOException {


            String fileLocation = "C:/Users/CC326628/Installs/TRY/Dotcoza/SiteMapUrlCheck.xlsx";
            FileInputStream file = new FileInputStream(new File(fileLocation));
            Workbook workbook = new XSSFWorkbook(file);

            Sheet sheet = workbook.getSheetAt(1);
            XSSFFont font = (XSSFFont) workbook.createFont();
            XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();

            Row row = sheet.getRow(ParamRow);
            if(row == null)
                row = sheet.createRow(ParamCol);
                Cell cellToUpdate = row.getCell(ParamCol);
            if(cellToUpdate == null)
                cellToUpdate = row.createCell(ParamCol);
            //PASS/FAIL Style here
        if(Status.equalsIgnoreCase("fail")) {
            font.setFontName("Comic Sans MS");
            font.setFontHeight(12.0);
            font.setBold(true);
            font.setColor(HSSFColor.WHITE.index);
            style.setFont(font);
            style.setFillForegroundColor(HSSFColor.RED.index);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cellToUpdate.setCellStyle(style);
            cellToUpdate.setCellValue(Status);
        }
        else{
            font.setFontName("Comic Sans MS");
            font.setFontHeight(12.0);
            font.setBold(false);
            cellToUpdate.setCellValue(Status);
            }
            FileOutputStream fileOutputStream = new FileOutputStream("C:/Users/CC326628/Installs/TRY/Dotcoza/SiteMapUrlCheck.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        }

}