package com.example.decorate.services;

import com.example.decorate.domain.*;
import com.example.decorate.domain.dto.*;
import com.example.decorate.mapper.*;
import com.example.decorate.repositorys.AttributeItemRepository;
import com.example.decorate.repositorys.AttributeRepository;
import com.example.decorate.repositorys.ProductKeyRepository;
import com.example.decorate.repositorys.curtain.CurtainRepository;
import com.example.decorate.repositorys.decoration.DecorationRepository;
import com.example.decorate.repositorys.furniture.FurnitureFabricRepository;
import com.example.decorate.repositorys.wallpaper.WallpaperRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.decorate.domain.ProductType.*;

@AllArgsConstructor
@Slf4j
@Service
@Transactional

public class ProductKeyService {
    private ImageMapper imageMapper;
    private AttributeMapper attributeMapper;
    private CurtainMapper curtainMapper;
    private WallpaperMapper wallpaperMapper;
    private DecorationMapper decorationMapper;
    private FurnitureFabricMapper furnitureFabricMapper;
    private ProductKeyRepository productKeyRepository;
    private CurtainRepository curtainRepository;
    private WallpaperRepository wallpaperRepository;
    private FurnitureFabricRepository furnitureFabricRepository;
    private DecorationRepository decorationRepository;
    private ImageService imageService;
    private AttributeItemRepository attributeItemRepository;
    private AttributeRepository attributeRepository;


    public ProductKey saveKey(ProductKey productKey, ProductType productType) {
        productKey.setType(productType);
        productKeyRepository.save(productKey);
        return productKey;
    }

    public List<ProductKey> getProductsFromLocal(String productIds) {
        List<ProductKey> productKeys = new ArrayList<>();
        String[] ids = productIds.split(",");
        System.out.println(ids);
        List<Long> productsLong = new ArrayList<>();
        for (String s : ids) {
            productsLong.add(Long.parseLong(s));
        }

        productsLong.stream().map(productKeyRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(productKeys::add);
        for (Long aLong : productsLong) {
        }
        return productKeys;
    }

    public List<ProductKey> getKeyholders(List<Long> itemId) {

        List<ProductKey> keys = new ArrayList<>();

        for (Long aLong : itemId) {
            Optional<ProductKey> keyHolder = this.productKeyRepository.findById(aLong);
            keyHolder.ifPresent(keys::add);
        }
        return keys;
    }

    public List<ProductListItem> getProducts(List<ProductKey> keys) {
        List<ProductListItem> items = new ArrayList<>();

        for (ProductKey productKey : keys) {
            if (productKey.getType() == WALLPAPER) {
                Optional<Wallpaper> byId = this.wallpaperRepository.findById(productKey.getId());
                if (byId.isPresent()) {
                    Wallpaper wallpaper = byId.get();
                    List<Image> images = imageService.getImagesByProductId(productKey);
                    images.forEach(img -> {
                        if (img.getImageType() == ImageType.PRIMARY_IMG) {
                            ProductListItem productListItem = new ProductListItem(wallpaper, new ImageModel(img));
                            items.add(productListItem);
                        }
                    });
                }
            }
            if (productKey.getType() == CURTAIN) {
                Optional<Curtain> byId = this.curtainRepository.findById(productKey.getId());
                if (byId.isPresent()) {
                    Curtain curtain = byId.get();
                    List<Image> images = imageService.getImagesByProductId(productKey);
                    images.forEach(img -> {
                        if (img.getImageType() == ImageType.PRIMARY_IMG) {
                            ProductListItem productListItem = new ProductListItem(curtain, new ImageModel(img));
                            items.add(productListItem);
                        }
                    });
                }
            }
        }
        return items;
    }

    public ProductListItem getProd(Long id) {
        Optional<ProductKey> keyHolder = this.productKeyRepository.findById(id);
        if (keyHolder.isPresent()) {
            if (keyHolder.get().getType() == WALLPAPER) {
                Optional<Wallpaper> wallpaper = this.wallpaperRepository.findById(keyHolder.get().getId());
                if (wallpaper.isPresent()) {
                    return new ProductListItem(wallpaper.get());
                }
            }
            if (keyHolder.get().getType() == CURTAIN) {
                Optional<Curtain> curtain = this.curtainRepository.findById(keyHolder.get().getId());
                if (curtain.isPresent()) {
                    return new ProductListItem(curtain.get());
                }
            }
        }
        return null;
    }

    public void deleteKeyHolder(ProductKey productKey) {
        productKeyRepository.delete(productKey);
    }

    public List<ProductCreationFormData> getProductsByFilter(String productCategory, List<Long> attrs) {
        /*List<ProductCreationFormData> products = new ArrayList<>();
        for (ProductType value : ProductType.values()) {
            if (value.toString().equals(productCategory)) {
                System.out.println(value + "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
                switch (value) {
                    case CURTAIN:
                        List<Curtain> curtainByAttributeDesc = curtainRepository.findCurtainByAttributeDesc(attrs, (long) attrs.size());
                        curtainByAttributeDesc.forEach(curtain -> {
                            ProductCreationFormData product = new ProductCreationFormData();
                            attrs.forEach(attribute -> attributeRepository.findByDescription(attribute));
                            curtainMapper.curtainToFormData(curtain, product);
                            product.setProductType(CURTAIN.getType());
                            products.add(product);
                        });
                        break;
                    case WALLPAPER:
                        List<Wallpaper> wallpaperByAttributeDesc = wallpaperRepository.findWallpaperByAttributeDesc(attrs, (long) attrs.size());
                        wallpaperByAttributeDesc.forEach(wallpaper -> {
                            ProductCreationFormData product = new ProductCreationFormData();
                            wallpaperMapper.wallpaperToFormData(wallpaper, product);
                            product.setProductType(WALLPAPER.getType());
                            products.add(product);
                        });
                        break;
                    case FURNITURE:
                        List<FurnitureFabric> furnitureFabricByAttributeDesc = furnitureFabricRepository.findFurnitureFabricByAttributeDesc(attrs, (long) attrs.size());
                        furnitureFabricByAttributeDesc.forEach(furniture -> {
                            ProductCreationFormData product = new ProductCreationFormData();
                            furnitureFabricMapper.furnitureFabricToFormData(furniture, product);
                            product.setProductType(FURNITURE.getType());
                            products.add(product);
                        });
                        break;
                    case DECORATION:
                        List<Decoration> decorationByAttributeDesc = decorationRepository.findDecorationByAttributeDesc(attrs, (long) attrs.size());
                        decorationByAttributeDesc.forEach(decoration -> {
                            ProductCreationFormData product = new ProductCreationFormData();
                            decorationMapper.decorationToFormData(decoration, product);
                            product.setProductType(DECORATION.getType());
                            products.add(product);
                        });
                        break;
                }
            }
        }
        setImageAndAttributes(products, attrs);
        return products;*/
        return null;
    }

    public void setImageAndAttributes(List<ProductCreationFormData> products, List<Long> attributeList) {
        products.forEach(productCreationFormData -> {
            List<AttributeItem> productAllAttributeItemsByProductId = attributeItemRepository.
                    findProductAllAttributeItemsByProductId(productCreationFormData.getId());
            List<Attribute> attributes = productAllAttributeItemsByProductId
                    .stream()
                    .map(AttributeItem::getAttribute)
                    .collect(Collectors.toList());
            List<Attribute> attributeListWithOutExtraType = removeNotNecessaryAttributeType(attributes, attributeList);
            List<AttributeCreationFormData> collect = attributeListWithOutExtraType
                    .stream()
                    .map(attribute -> attributeMapper.createAttributeFormData(attribute))
                    .collect(Collectors.toList());
            productCreationFormData.getAttributeCreationFormDataList().addAll(collect);
            List<Image> allImagesByProdId =
                    imageService.getAllImagesByProdId(productCreationFormData.getId());
            List<ImageModel> imageModelList = allImagesByProdId
                    .stream()
                    .map(image -> imageMapper.imageModelFromImage(image))
                    .collect(Collectors.toList());
            productCreationFormData.getImageList().addAll(imageModelList);
        });
    }

    private List<Attribute> removeNotNecessaryAttributeType(List<Attribute> attributes, List<Long> attrs) {
        List<Attribute> attributeList = new ArrayList<>();
     /*   attributes.removeIf(attribute -> attribute.getType() == AttributeType.TYPE);
        attributeList.addAll(attributes);
        attrs.forEach(attribute -> {
            Optional<Attribute> byDescription = attributeRepository.findByDescription(attribute);
            if (byDescription.get().getType() == AttributeType.TYPE) {
                attributeList.add(byDescription.get());
            }
        });*/
        return attributeList;
    }

    public List<ProductCategoryModalDto> getAllProductTypeWithAttributes() {
        List<ProductCategoryModalDto> productCategoryModalDtoList = new ArrayList<>();
        List<Attribute> curtainSubTypes = attributeRepository.getAllCurtainSubType();
        curtainSubTypes.forEach(attribute -> {
            ProductCategoryModalDto productCategoryModalDto = new ProductCategoryModalDto();
            List<AttributeItem> productKeysForCurtainSubtype = attributeItemRepository.getProductKeysForCurtainSubtype(attribute);
            productKeysForCurtainSubtype.forEach(attributeItem -> {
                List<AttributeItem> productAllAttributeItemsByProductKey = attributeItemRepository.findProductAllAttributeItemsByProductKey(attributeItem.getProductKey());
                List<Long> allAttributeId = getAllAttributeId(productAllAttributeItemsByProductKey);
                List<Attribute> allAttributeDescription = attributeRepository.getAllAttributeDescription(allAttributeId);
                productCategoryModalDto.setProductType(attributeItem.getAttribute().getDescription());
                sortAttributes(allAttributeDescription, productCategoryModalDto);
            });
            productCategoryModalDtoList.add(productCategoryModalDto);
        });

        productCategoryModalDtoList.addAll(getAllAttributesByProductType());
        return productCategoryModalDtoList;
    }

    public List<ProductCategoryModalDto> getAllAttributesByProductType() {

        List<ProductCategoryModalDto> productCategoryModalDtoList = new ArrayList<>();
        for (ProductType value : ProductType.values()) {
            if (value != CURTAIN) {
                ProductCategoryModalDto product = new ProductCategoryModalDto();
                product.setProductDatabaseName(value.toString());
                product.setProductType(value.getType());
                List<ProductKey> byProductType = this.productKeyRepository.findByProductType(value);
                byProductType.forEach(productKey -> {
                    List<AttributeItem> productAllAttributeItemsByProductKey = attributeItemRepository.findProductAllAttributeItemsByProductKey(productKey);
                    List<Long> allAttributeId = getAllAttributeId(productAllAttributeItemsByProductKey);
                    List<Attribute> allAttributeDescription = attributeRepository.getAllAttributeDescription(allAttributeId);
                    sortAttributes(allAttributeDescription, product);
                    SearchModel searchModel = SearchModel.builder()
                            .productType(product.getProductDatabaseName())
                            .build();
                    product.setSearchModel(searchModel);
                });
                productCategoryModalDtoList.add(product);
            }
        }
        return productCategoryModalDtoList;
    }

    public void sortAttributes(List<Attribute> attributeList, ProductCategoryModalDto productCategoryModalDto) {

       /* attributeList.stream()
                .filter(attribute -> attribute.getType() == AttributeType.COLOR)
                .forEach(attribute -> productCategoryModalDto.getColorList()
                        .add(attribute));
        attributeList.stream()
                .filter(attribute -> attribute.getType() == AttributeType.PATTERN)
                .forEach(attribute -> productCategoryModalDto.getPatternList()
                        .add(attribute));
        attributeList.stream()
                .filter(attribute -> attribute.getType() == AttributeType.STYLE)
                .forEach(attribute -> productCategoryModalDto.getStyleList()
                        .add(attribute));*/
    }

    public List<Long> getAllAttributeId(List<AttributeItem> attributeItemList) {
        return attributeItemList.stream()
                .map(attributeItem -> attributeItem.getAttribute()
                        .getId())
                .collect(Collectors.toList());
    }

    public List<ProductCreationFormData> getProductsWithCurtainSubType(String productCategory) {
        List<ProductCreationFormData> products = new ArrayList<>();
        for (ProductType value : values()) {
            if (value.getType().equals(productCategory)) {
                products.addAll(getProductsWithType(value));
            }
        }
        return products;
    }

    public List<ProductCreationFormData> getProductsWithType(ProductType productType) {
        List<ProductCreationFormData> products = new ArrayList<>();
        switch (productType) {
            case CURTAIN:
                List<Curtain> allCurtains = curtainRepository.getAllCurtains();
                allCurtains.forEach(curtain -> {
                    ProductCreationFormData product = new ProductCreationFormData();
                    curtainMapper.curtainToFormData(curtain, product);
                    product.setProductType(CURTAIN.getType());
                    products.add(product);
                });
                break;
            case WALLPAPER:
                List<Wallpaper> allWallpaper = wallpaperRepository.getAllWallpaper();
                allWallpaper.forEach(wallpaper -> {
                    ProductCreationFormData product = new ProductCreationFormData();
                    wallpaperMapper.wallpaperToFormData(wallpaper, product);
                    product.setProductType(WALLPAPER.getType());
                    products.add(product);
                });
                break;
            case FURNITURE:
                List<FurnitureFabric> allFurniture = furnitureFabricRepository.getAllFurniture();
                allFurniture.forEach(furniture -> {
                    ProductCreationFormData product = new ProductCreationFormData();
                    furnitureFabricMapper.furnitureFabricToFormData(furniture, product);
                    product.setProductType(FURNITURE.getType());
                    products.add(product);
                });
                break;
            case DECORATION:
                List<Decoration> allDecoration = decorationRepository.getAllDecorations();
                allDecoration.forEach(decoration -> {
                    ProductCreationFormData product = new ProductCreationFormData();
                    decorationMapper.decorationToFormData(decoration, product);
                    product.setProductType(DECORATION.getType());
                    products.add(product);
                });
                break;
        }
        products.forEach(productCreationFormData -> {
            List<AttributeItem> productAllAttributeItemsByProductId = attributeItemRepository.
                    findProductAllAttributeItemsByProductId(productCreationFormData.getId());
            List<Attribute> attributes = productAllAttributeItemsByProductId
                    .stream()
                    .map(AttributeItem::getAttribute)
                    .collect(Collectors.toList());
            List<AttributeCreationFormData> collect = attributes
                    .stream()
                    .map(attribute -> attributeMapper.createAttributeFormData(attribute))
                    .collect(Collectors.toList());
            productCreationFormData.getAttributeCreationFormDataList().addAll(collect);
            List<Image> allImagesByProdId =
                    imageService.getAllImagesByProdId(productCreationFormData.getId());
            List<ImageModel> imageModelList = allImagesByProdId
                    .stream()
                    .map(image -> imageMapper.imageModelFromImage(image))
                    .collect(Collectors.toList());
            productCreationFormData.getImageList().addAll(imageModelList);
        });
        return products;
    }
}
