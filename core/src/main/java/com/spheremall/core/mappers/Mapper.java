package com.spheremall.core.mappers;

import java.util.List;

public interface Mapper<IN, OUT> {

    List<OUT> doObject(IN obj);
}
