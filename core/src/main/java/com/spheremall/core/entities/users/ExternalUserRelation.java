package com.spheremall.core.entities.users;

import com.spheremall.core.entities.Entity;
import com.spheremall.core.jsonapi.annotations.Type;

@Type("externalUserRelation")
public class ExternalUserRelation extends Entity {
    public int userId;
    public String relatedUserId;
    public String additionalInfo;
    public String relationType;
}
