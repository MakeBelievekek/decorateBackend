package com.example.decorate.service;

import com.example.decorate.domain.dto.AttributeCreationFormData;
import com.example.decorate.domain.dto.AttributeListItemData;
import com.example.decorate.domain.dto.ExcelData;
import com.example.decorate.domain.dto.ImageData;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    //  public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String TYPE = "application/octet-stream";
    static String[] HEADERs = {"Id", "Title", "Description", "Published"};
    static String SHEET = "Sheet1";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<ExcelData> excelToTutorials(InputStream is) {
        try {

            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<ExcelData> excelDataList = new ArrayList<ExcelData>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                ExcelData excelData = new ExcelData();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            excelData.setName(currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 1:
                            excelData.setItemNumber(currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 2:
                            excelData.setPrice((int) currentCell.getNumericCellValue());
                            System.out.println((int) currentCell.getNumericCellValue());
                            break;
                        case 3:
                            excelData.setTypeOfProduct(currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 4:
                            excelData.setCurtainType(currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 5:
                            excelData.setProductFamily(currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 6:
                            excelData.setProductDesc(currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 7:
                            excelData.setComposition(currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 8:
                            excelData.setHeight((int) currentCell.getNumericCellValue());
                            System.out.println((int) currentCell.getNumericCellValue());
                            break;
                        case 9:
                            excelData.setWidth((int) currentCell.getNumericCellValue());
                            System.out.println((int) currentCell.getNumericCellValue());
                            break;
                        case 10:
                            excelData.setPatternRep((int) currentCell.getNumericCellValue());
                            System.out.println((int) currentCell.getNumericCellValue());
                            break;
                        case 11:
                            excelData.setTypeOfSize(currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 12:
                            excelData.setCleaningInst(currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 13:
                            excelData.setRecommendedGlue(currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                        case 14:
                            excelData.setAnnotation(currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                        case 15:
                            excelData.setAbrasionResistance(currentCell.getStringCellValue());
                            System.out.println(currentCell.getStringCellValue());
                        case 16:
                            excelData.getImageList().add(new ImageData("PRIMARY_KEY", currentCell.getStringCellValue()));
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 17:
                            if (currentCell.getStringCellValue() != null) {
                                for (ImageData imageData : imageSlicer(currentCell.getStringCellValue())) {
                                    excelData.getImageList().add(imageData);
                                    System.out.println(imageData);
                                }
                                System.out.println(currentCell.getStringCellValue());
                            }
                            break;
                        case 18:
                            setAttributes(excelData, currentCell.getStringCellValue(), cellIdx);
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 19:
                            setAttributes(excelData, currentCell.getStringCellValue(), cellIdx);
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        case 20:
                            setAttributes(excelData, currentCell.getStringCellValue(), cellIdx);
                            System.out.println(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                excelDataList.add(excelData);
            }
            workbook.close();

            return excelDataList;

        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<ImageData> imageSlicer(String images) {
        List<String> urls = Arrays.asList(images.split(","));
        List<ImageData> secImages = new ArrayList<>();
        for (String url : urls) {
            secImages.add(new ImageData("SECONDARY_KEY", url));
        }
        return secImages;
    }

    public static void setAttributes(ExcelData excelData, String stringCellValue, int cellIndex) {
        List<String> attr = Arrays.asList(stringCellValue.split(","));
        for (String s : attr) {
            if (cellIndex == 18) {
                excelData.getAttributes().add(new AttributeCreationFormData("COLOR", s));
            }
            if (cellIndex == 19) {
                excelData.getAttributes().add(new AttributeCreationFormData("PATTERN", s));
            }
            if (cellIndex == 20) {
                excelData.getAttributes().add(new AttributeCreationFormData("STYLE", s));
            }
        }
    }
}
