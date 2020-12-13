package com.example.decorate.services;

import com.example.decorate.domain.AttributeType;
import com.example.decorate.domain.dto.AttributeCreationFormData;
import com.example.decorate.domain.dto.ImageModel;
import com.example.decorate.domain.dto.ProductCreationFormData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.example.decorate.domain.AttributeType.*;
import static com.example.decorate.domain.ImageType.PRIMARY_IMG;
import static com.example.decorate.domain.ImageType.SECONDARY_IMG;

public class CsvResolver {

    public static String TYPE = "application/octet-stream";
    static String[] HEADERS = {"Terméknév", "Cikkszám", "Ár", "Termék Típus", "Függöny típus", "Termékcsalád", "Leírás",
            "Összetétel", "Magasság", "Szélesség", "Mintaismétlődés", "Mértékegység", "Tisztítási útmutató",
            "Ajánlott Ragasztó", "Falra felrakás módja", "Kopásállóság", "Fő kép", "Másodlagos Képek", "Szín", "Minta", "Stílus"};

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }
    public static List<ProductCreationFormData> csvPars(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<ProductCreationFormData> products = new ArrayList<ProductCreationFormData>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                ProductCreationFormData product = new ProductCreationFormData();
                product.setProductType(csvRecord.get("Termék Típus"));
                System.out.println(csvRecord.get("Termék Típus"));
                product.setName(csvRecord.get("Terméknév"));
                System.out.println(csvRecord.get("Terméknév"));
                product.setItemNumber(csvRecord.get("Cikkszám"));
                System.out.println(csvRecord.get("Cikkszám"));
                product.setPrice(csvRecord.get("Ár").equals("") ? 0 :Integer.parseInt(csvRecord.get("Ár")));
                System.out.println(Integer.parseInt(csvRecord.get("Ár")));
                product.setProductFamily(csvRecord.get("Termékcsalád"));
                System.out.println(csvRecord.get("Termékcsalád"));
                product.setProductDesc(csvRecord.get("Leírás"));
                System.out.println(csvRecord.get("Leírás"));
                product.setComposition(csvRecord.get("Összetétel"));
                System.out.println(csvRecord.get("Összetétel"));
                product.setHeight(csvRecord.get("Magasság").equals("") ? 0 : Integer.parseInt(csvRecord.get("Magasság")));
                System.out.println(product.getHeight());
                product.setWidth(csvRecord.get("Szélesség").equals("") ? 0 : Integer.parseInt(csvRecord.get("Szélesség")));
                product.setAbrasionResistance(csvRecord.get("Kopásállóság").equals("") ? 0 : Integer.parseInt(csvRecord.get("Kopásállóság")));
                product.setPatternRep(csvRecord.get("Mintaismétlődés").equals("") ? 0 : Double.parseDouble(csvRecord.get("Mintaismétlődés")));
                System.out.println(product.getWidth());
                System.out.println(product.getPatternRep());
                product.setTypeOfSize(csvRecord.get("Mértékegység"));
                System.out.println(csvRecord.get("Mértékegység"));
                product.setCleaningInst(csvRecord.get("Tisztítási útmutató"));
                product.setRecommendedGlue(csvRecord.get("Ajánlott Ragasztó"));
                product.setAnnotation(csvRecord.get("Falra felrakás módja"));
                String colors = csvRecord.get("Szín");
                String patterns = csvRecord.get("Minta");
                String styles = csvRecord.get("Stílus");
                String curtainType = csvRecord.get("Függöny típus");
                setAttributes(product, colors, patterns, styles, curtainType);
                product.getImageList().add(new ImageModel(PRIMARY_IMG.toString(), csvRecord.get("Fő kép")));
                imageSlicer(product, csvRecord.get("Másodlagos Képek"));
                products.add(product);
            }
            for (ProductCreationFormData product : products) {
                System.out.println(product);
            }
            return products;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static void imageSlicer(ProductCreationFormData product, String images) {
        if (!images.equals("")) {
            String[] urls = images.split(",");
            for (String url : urls) {
                product.getImageList().add(new ImageModel(SECONDARY_IMG.toString(), url));
            }
        }
    }

    public static void setAttributes(ProductCreationFormData product, String colors, String patterns, String styles, String curtainType) {
        System.out.println(colors);
        System.out.println(patterns);
        System.out.println(styles);
        System.out.println(curtainType);
        System.out.println(product);
        String[] colorList = colors.split(",");
        String[] patternList = patterns.split(",");
        String[] styleList = styles.split(",");
        String[] typeList = curtainType.split(",");

        for (String c : colorList) {
            product.getAttributeCreationFormDataList().add(new AttributeCreationFormData(COLOR.toString(), c));
        }
        for (String p : patternList) {
            product.getAttributeCreationFormDataList().add(new AttributeCreationFormData(PATTERN.toString(), p));
        }
        for (String s : styleList) {
            product.getAttributeCreationFormDataList().add(new AttributeCreationFormData(STYLE.toString(), s));
        }
        for (String t : typeList) {
            product.getAttributeCreationFormDataList().add(new AttributeCreationFormData(AttributeType.TYPE.toString(), t));
        }
    }

}
