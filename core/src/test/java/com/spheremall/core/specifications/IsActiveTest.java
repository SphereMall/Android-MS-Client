package com.spheremall.core.specifications;

import com.spheremall.core.entitities.EmptyEntity;
import com.spheremall.core.entitities.EntityWithActiveField;
import com.spheremall.core.specifications.base.IsActive;

import org.junit.Assert;
import org.junit.Test;

public class IsActiveTest {

    @Test(expected = IllegalArgumentException.class)
    public void testIsActiveSpecificationException() {
        IsActive<EmptyEntity> entityIsActive = new IsActive<>();
        entityIsActive.isSatisfied(new EmptyEntity());

    }

    @Test
    public void testIsActiveSpecification() {
        EntityWithActiveField entity = new EntityWithActiveField();
        entity.setActive(false);

        IsActive<EntityWithActiveField> productIsActive = new IsActive<>();
        Assert.assertFalse(productIsActive.isSatisfied(entity));

        entity.setActive(true);
        Assert.assertTrue(productIsActive.isSatisfied(entity));
    }
}
