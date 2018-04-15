/*
 * FILE     :  LdapAttribute.java
 *
 * CLASS    :  LdapAttribute
 *
 * COPYRIGHT:
 *
 * The computer systems, procedures, data bases and programs created and
 * maintained by DST Systems, Inc., are proprietary in nature and as such
 * are confidential.  Any unauthorized use or disclosure of such information
 * may result in civil liabilities.
 *
 * Copyright 2018 by DST Systems, Inc.
 * All Rights Reserved.
 */
package hello;

/**
 * Constant declarations that identifies the fields we will retrieve from LDAP
 * during authentication.
 * 
 * @author dt63314
 */
public enum LdapAttribute
{
	SAM_ACCOUNT_NAME("sAMAccountName"),
	GIVEN_NAME("givenName"),
	SURNAME("sn"),
	EMAIL("mail"),
	MEMBER_OF("memberOf"),
	DISTINGUISHED_NAME("distinguishedName");

	public final String value;

	private LdapAttribute(String value)
	{
		this.value = value;
	}
}
