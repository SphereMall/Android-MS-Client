package com.spheremall.core.resources.products;

import android.annotation.SuppressLint;

import com.spheremall.core.SMClient;
import com.spheremall.core.entities.Entity;
import com.spheremall.core.entities.products.Attribute;
import com.spheremall.core.entities.products.AttributeValue;
import com.spheremall.core.entities.products.Product;
import com.spheremall.core.entities.products.ProductVariant;
import com.spheremall.core.entities.products.ProductVariantsContainer;
import com.spheremall.core.exceptions.SphereMallException;
import com.spheremall.core.filters.elasticsearch.ESSearchFilter;
import com.spheremall.core.filters.elasticsearch.compound.BoolFilter;
import com.spheremall.core.filters.elasticsearch.criterions.TermsFilterCriteria;
import com.spheremall.core.filters.elasticsearch.terms.TermsFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductVariantsBuilder {

    protected SMClient client;
    protected ProductAttributesBuilder attributesBuilder;

    public ProductVariantsBuilder(SMClient client, ProductAttributesBuilder attributesBuilder) {
        this.client = client;
        this.attributesBuilder = attributesBuilder;
    }

    public List<ProductVariantsContainer> build(List<String> attributeCodes, List<ProductVariant> productVariants) throws IOException, SphereMallException {

        List<String> relationIds = new ArrayList<>();

        for (ProductVariant variant : productVariants) {
            relationIds.add(variant.relationId);
        }

        List<Entity> entities = fetchProductsByRelations(relationIds);

        Map<Integer, String> variantAndIdRelation = getVariantAndIdRelation(productVariants, entities);

        Map<String, Map<String, Product>> compoundMap = new HashMap<>();

        for (Entity entity : entities) {

            Product product = attributesBuilder.combineProductProperties((Product) entity);

            Map<String, Product> variants = compoundMap.get(product.variantsCompound);
            if (variants == null) {
                variants = new HashMap<>();
            }

            String compoundString = buildCompoundKeyString(attributeCodes, product);

            if (variants.size() == 0 || !variants.containsKey(compoundString)) {
                variants.put(compoundString, product);
                compoundMap.put(product.variantsCompound, variants);
            }
        }

        return createContainersWithVariants(variantAndIdRelation, compoundMap);
    }

    private List<Entity> fetchProductsByRelations(List<String> relationIds) throws SphereMallException, IOException {

        TermsFilter termsFilter = new TermsFilter(
                new TermsFilterCriteria("variantsCompound", relationIds));

        BoolFilter boolFilter = new BoolFilter();
        boolFilter.filter(termsFilter);

        ESSearchFilter filter = new ESSearchFilter();
        filter.index("sm-products")
                .query(boolFilter);

        return client.elasticSearch()
                .filters(filter.asFilter())
                .limit(100)
                .fetch().data();
    }

    private String buildCompoundKeyString(List<String> attributeCodes, Product product) {
        StringBuilder compoundStringBuilder = new StringBuilder();
        for (String code : attributeCodes) {
            AttributeValue attributeValue = firstAttributeValueByCodeOrNull(product, code);
            if (attributeValue != null) {
                compoundStringBuilder
                        .append("_")
                        .append(attributeValue.value);
            }
        }

        return compoundStringBuilder.toString();
    }

    @SuppressLint("UseSparseArrays")
    private Map<Integer, String> getVariantAndIdRelation(List<ProductVariant> productVariants, List<Entity> entities) {
        Map<Integer, String> variantAndIdRelation = new HashMap<>();
        for (Entity entity : entities) {
            for (ProductVariant variant : productVariants) {
                if (entity.getId().equals(variant.productId)) {
                    Product product = (Product) entity;
                    variantAndIdRelation.put(product.getId(), product.variantsCompound);
                }
            }
        }
        return variantAndIdRelation;
    }

    private AttributeValue firstAttributeValueByCodeOrNull(Product product, String code) {
        for (Attribute attr : product.attributes) {
            if (attr.code.equals(code)) {
                if (attr.attributeValues.size() > 0) {
                    return attr.attributeValues.get(0);
                }
            }
        }
        return null;
    }

    private List<ProductVariantsContainer> createContainersWithVariants(Map<Integer, String> variantAndIdRelation, Map<String, Map<String, Product>> compoundMap) {
        List<ProductVariantsContainer> productVariantsContainers = new ArrayList<>();

        for (Map.Entry<Integer, String> entry : variantAndIdRelation.entrySet()) {
            Map<String, Product> map = compoundMap.get(entry.getValue());

            ProductVariantsContainer container = new ProductVariantsContainer();
            container.relationProductId = entry.getKey();

            for (Map.Entry<String, Product> item : map.entrySet()) {
                container.variants.add(item.getValue());
            }
            productVariantsContainers.add(container);
        }
        return productVariantsContainers;
    }
}
