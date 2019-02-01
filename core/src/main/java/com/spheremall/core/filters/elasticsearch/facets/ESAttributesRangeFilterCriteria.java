package com.spheremall.core.filters.elasticsearch.facets;

import com.spheremall.core.filters.elasticsearch.common.ElasticSearchQuery;
import com.spheremall.core.filters.elasticsearch.terms.PriceRangeFilter;

import java.util.Collections;
import java.util.List;

public class ESAttributesRangeFilterCriteria extends ESAttributesFilterCriteria {

    public ESAttributesRangeFilterCriteria(String code, String... value) {
        super(code, value);
    }

    @Override
    public List<ElasticSearchQuery> toQuery() {
        if (values.size() > 0) {
            Collections.sort(values);
            double min = 0;
            double max;
            if (values.size() > 1) {
                min = Double.valueOf(values.get(0));
                max = Double.valueOf(values.get(1));
            } else {
                max = Double.valueOf(values.get(0));
            }

            PriceRangeFilter priceRangeFilter = new PriceRangeFilter(code + "_attr.attributeValue");
            priceRangeFilter.setRange(min, max);
            return Collections.singletonList(priceRangeFilter);
        } else {
            return Collections.emptyList();
        }
    }
}
