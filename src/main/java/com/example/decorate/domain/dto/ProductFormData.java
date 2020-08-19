package com.example.decorate.domain.dto;
import java.util.List;

public class ProductFormData {


    private String name;
    private String productDesc;
    private int itemNumber;
    private int width;
    private int patternRep;
    private int price;
    private String composition;
    private List<AttributeListItemData> attributeListItemData;
    private List<ImageData> imageList;
    private String productFamily;
    private String annotation;
    private String recommendedGlue;


    public List<AttributeListItemData> getAttributeListItemData() {
        return attributeListItemData;
    }

    public void setAttributeListItemData(List<AttributeListItemData> attributeListItemData) {
        this.attributeListItemData = attributeListItemData;
    }

    public List<ImageData> getImageList() {
        return imageList;
    }

    public void setImageList(List<ImageData> imageList) {
        this.imageList = imageList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getPatternRep() {
        return patternRep;
    }

    public void setPatternRep(int patternRep) {
        this.patternRep = patternRep;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getProductFamily() {
        return productFamily;
    }

    public void setProductFamily(String productFamily) {
        this.productFamily = productFamily;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getRecommendedGlue() {
        return recommendedGlue;
    }

    public void setRecommendedGlue(String recommendedGlue) {
        this.recommendedGlue = recommendedGlue;
    }

    @Override
    public String toString() {
        return "ProductFormData{" +
                "name='" + name + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", itemNumber=" + itemNumber +
                ", width=" + width +
                ", patternRep=" + patternRep +
                ", price=" + price +
                ", composition='" + composition + '\'' +
                ", attributeListItemData=" + attributeListItemData +
                ", imageList=" + imageList +
                ", productFamily='" + productFamily + '\'' +
                ", annotation='" + annotation + '\'' +
                ", recommendedGlue='" + recommendedGlue + '\'' +
                '}';
    }
}
