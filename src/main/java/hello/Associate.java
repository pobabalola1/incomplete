/*
 * FILE : Associate.java
 *
 * CLASS : Associate
 *
 * COPYRIGHT:
 *
 * The computer systems, procedures, data bases and programs created and maintained by DST Systems, Inc., are proprietary in nature and as such are
 * confidential. Any unauthorized use or disclosure of such information may result in civil liabilities.
 *
 * Copyright 2018 by DST Systems, Inc. All Rights Reserved.
 */
package hello;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;

/**
 * Bean representing an authenticated Associate with a role in the Admin Tool.
 * @author dt63314
 *
 */
@Data
public class Associate implements Serializable
{
    private static final long serialVersionUID = 2859878362527358747L;
    
    private String            id;
    private String            firstName;
    private String            lastName;
    private String            email;
    private Set<?>            roles;
    private String[]          permissions;
    private String            role;
    /**
     * @return true if the associate has a role assignment in the Admin Tool, otherwise false.
     */
    public boolean hasRole()
    {
        return role != null && !(role.isEmpty());
    }
    
}
