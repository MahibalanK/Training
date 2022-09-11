package TestCode;


import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.*;
import java.util.LinkedList;


public class ExcelToJson {


    public static void main(String args[]) throws IOException, InterruptedException {
        String fileLocation = "C:/Users/CC326628/Installs/TRY/Dotcoza/SampleData-2.xlsx";
        FileInputStream file = new FileInputStream(new File(fileLocation));
        Workbook workbook = new XSSFWorkbook(file);

        Sheet sheet = workbook.getSheetAt(3);

        LinkedList<String> colHeader = new LinkedList<String>();
        FileWriter fw=new FileWriter("C:/Users/CC326628/Installs/TRY/Dotcoza/JSON.txt");
        String FirstColData,fourthColData,keyname,keyval;
        String FourthColData;
        Cell cell;
        for(int rowNumber = 1; rowNumber < sheet.getLastRowNum()+1; rowNumber++) {
            Row row = sheet.getRow(rowNumber);

            for (int columnNumber = 0; columnNumber <=row.getLastCellNum(); columnNumber++) {
                 cell = row.getCell(columnNumber);
                    if( (columnNumber==1) && (cell.getStringCellValue().equalsIgnoreCase("N"))) {
                        cell = row.getCell(columnNumber-1);
                        FirstColData = cell.getStringCellValue();
                        System.out.println("List name  with N flag " + FirstColData);
                        try{
                            cell = row.getCell(columnNumber+2);
                            fourthColData = cell.getStringCellValue();
                            System.out.println("actual value  " + fourthColData);
                                    fw.write( '"'+FirstColData+ "\":[" +"\r\n");
                                    fw.write( '"'+fourthColData+ "\"],"+"\r\n");
                                fw.flush();

                        }catch(Exception e){System.out.println(e);}
                        System.out.println("Success...");
                    }

                if( (columnNumber==0) && (cell.getStringCellValue().equalsIgnoreCase("N"))) {
                    cell = row.getCell(columnNumber);
                    FirstColData = cell.getStringCellValue();
                    System.out.println("List name  with N flag " + FirstColData);
                    try{
                        DataFormatter formatter = new DataFormatter();
                        cell = row.getCell(columnNumber+2);
                        keyname = formatter.formatCellValue(cell);
                        System.out.println("Direct key name   " + keyname);
                        cell = row.getCell(columnNumber+3);
                        keyval = formatter.formatCellValue(cell);
                        System.out.println("Direct key value   " + keyval);
                        fw.write( '"'+keyname+ "\":" +"\r\n");
                        fw.write( '"'+keyval+ "\","+"\r\n");
                        fw.flush();

                    }catch(Exception e){System.out.println(e);}
                    System.out.println("Success...");
                }



                if( (columnNumber==1) && (cell.getStringCellValue().equalsIgnoreCase("Y"))) {
                    cell = row.getCell(columnNumber-1);
                    FirstColData = cell.getStringCellValue();
                    System.out.println("List name  with Y flag " + FirstColData);
                    try{
                        cell = row.getCell(columnNumber+1);
                        keyname = cell.getStringCellValue();
                        System.out.println("Key name  " + keyname);
                        fw.write( '"'+FirstColData+ "\":[{" +"\r\n");

                        cell = row.getCell(columnNumber+2);
                        keyval = cell.getStringCellValue();
                        System.out.println("Key value  " + keyval);
                        fw.write( '"'+keyname+ "\":"+'"'+keyval+ "\","+"\r\n");

                        cell = row.getCell(columnNumber+3);
                        keyname = cell.getStringCellValue();
                        System.out.println("Second key name   " + keyname);
                        if(!keyname.isEmpty()){
                            cell = row.getCell(columnNumber+4);
                            keyval = cell.getStringCellValue();
                            fw.write( '"'+keyname+ "\":"+'"'+keyval+ "\","+"\r\n");
                        }
                        fw.write( "\"}]");
                        fw.flush();

                    }catch(Exception e){System.out.println(e);}
                    System.out.println("Success...");
                }

            }
            }
        fw.close();
            }

        }