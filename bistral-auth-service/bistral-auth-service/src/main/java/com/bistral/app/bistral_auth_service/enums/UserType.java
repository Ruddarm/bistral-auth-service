package com.bistral.app.bistral_auth_service.enums;


/**
 * UserType Enum represent type of tenantUser in system
 * <ul>
 *     <li><Code>System TenantUser</Code> represent tenantUser type of system mean can access api related to system only</li>
 *     <li><code>Tenant TenantUser</code> represent tenantUser tenant can access resources allowed to them only</li>
 * </ul>
 *
 */
public enum UserType {
    CUSTOMER,
    SYSTEM_USER,
    TENANT_USER
}
