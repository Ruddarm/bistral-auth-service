
CREATE TABLE system_users(
    user_id UUID PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL,
    password_hash VARCHAR(300) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ
);

CREATE UNIQUE INDEX uq_system_users_username
ON system_users (LOWER(user_name))
WHERE deleted_at IS NULL;


-- =========================================================
-- 2. TENANT USER
-- One global identity within a tenant.
-- A user can access multiple bistros.
-- =========================================================

CREATE TABLE tenant_user (
    user_id UUID PRIMARY KEY,
    tenant_id UUID NOT NULL,
    user_name VARCHAR(100) NOT NULL,
    user_email VARCHAR(100) NOT NULL,
    password_hash VARCHAR(300) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ,
    deleted_at TIMESTAMPTZ,

    CONSTRAINT uq_tenant_user_tenant_user
        UNIQUE (tenant_id, user_id)
);

CREATE UNIQUE INDEX uq_tenant_user_username
ON tenant_user (tenant_id, LOWER(user_name))
WHERE deleted_at IS NULL;

CREATE UNIQUE INDEX uq_tenant_user_email
ON tenant_user (tenant_id, LOWER(user_email))
WHERE deleted_at IS NULL;


-- =========================================================
-- 3. RESOURCE
-- System-managed resources.
-- Examples: ORDER, MENU, BRANCH, EMPLOYEE
-- =========================================================

CREATE TABLE resource (
    resource_id UUID PRIMARY KEY,
    resource_name VARCHAR(100) NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by UUID NOT NULL REFERENCES system_users(user_id),

    updated_at TIMESTAMPTZ,
    updated_by UUID REFERENCES system_users(user_id),

    deleted_at TIMESTAMPTZ
);

CREATE UNIQUE INDEX uq_resource_name
ON resource (LOWER(resource_name))
WHERE deleted_at IS NULL;


-- =========================================================
-- 4. ACTIONS
-- System-managed actions.
-- Examples: CREATE, READ, UPDATE, DELETE
-- =========================================================

CREATE TABLE actions (
    action_id UUID PRIMARY KEY,
    action_name VARCHAR(100) NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by UUID NOT NULL REFERENCES system_users(user_id),

    updated_at TIMESTAMPTZ,
    updated_by UUID REFERENCES system_users(user_id),

    deleted_at TIMESTAMPTZ
);

CREATE UNIQUE INDEX uq_action_name
ON actions (LOWER(action_name))
WHERE deleted_at IS NULL;


-- =========================================================
-- 5. RESOURCE ACTION MAPPING
-- Defines which actions are available for which resources.
-- Example: ORDER + CREATE
-- =========================================================

CREATE TABLE resource_action_mapping (
    resource_action_mapping_id UUID PRIMARY KEY,

    resource_id UUID NOT NULL
        REFERENCES resource(resource_id),

    action_id UUID NOT NULL
        REFERENCES actions(action_id),

    is_system_specific BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by UUID NOT NULL REFERENCES system_users(user_id),

    updated_at TIMESTAMPTZ,
    updated_by UUID REFERENCES system_users(user_id),

    deleted_at TIMESTAMPTZ
);

CREATE UNIQUE INDEX uq_resource_action_mapping
ON resource_action_mapping (resource_id, action_id)
WHERE deleted_at IS NULL;


-- =========================================================
-- 6. TENANT ROLE
-- Roles belong to a particular bistro.
-- Example: Manager, Cashier, Branch Admin
-- =========================================================

CREATE TABLE tenant_role (
    tenant_role_id UUID PRIMARY KEY,
    tenant_id UUID NOT NULL,
    bistro_id UUID NOT NULL,
    parent_role_id UUID,
    role_name VARCHAR(100) NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by UUID NOT NULL,

    updated_at TIMESTAMPTZ,
    updated_by UUID,

    deleted_at TIMESTAMPTZ,

    CONSTRAINT uq_tenant_role_reference
        UNIQUE (tenant_id, bistro_id, tenant_role_id),

    CONSTRAINT fk_tenant_role_parent
        FOREIGN KEY (tenant_id, bistro_id, parent_role_id)
        REFERENCES tenant_role (tenant_id, bistro_id, tenant_role_id),
    CONSTRAINT fk_tenant_role_created_by
        FOREIGN KEY (tenant_id, created_by)
        REFERENCES tenant_user (tenant_id, user_id),

    CONSTRAINT fk_tenant_role_updated_by
        FOREIGN KEY (tenant_id, updated_by)
        REFERENCES tenant_user (tenant_id, user_id)
);

CREATE UNIQUE INDEX uq_tenant_role_name
ON tenant_role (tenant_id, bistro_id, LOWER(role_name))
WHERE deleted_at IS NULL;


-- =========================================================
-- 7. TENANT ROLE PERMISSIONS
-- Assigns resource-action mappings to tenant roles.
-- =========================================================

CREATE TABLE tenant_role_permissions (
    role_permission_id UUID PRIMARY KEY,

    tenant_id UUID NOT NULL,
    bistro_id UUID NOT NULL,

    tenant_role_id UUID NOT NULL
        REFERENCES tenant_role(tenant_role_id),

    resource_action_mapping_id UUID NOT NULL
        REFERENCES resource_action_mapping(resource_action_mapping_id),

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by UUID NOT NULL,

    updated_at TIMESTAMPTZ,
    updated_by UUID,

    deleted_at TIMESTAMPTZ,

    CONSTRAINT fk_role_permission_role
        FOREIGN KEY (tenant_id, bistro_id, tenant_role_id)
        REFERENCES tenant_role (tenant_id, bistro_id, tenant_role_id),

    CONSTRAINT fk_role_permission_created_by
        FOREIGN KEY (tenant_id, created_by)
        REFERENCES tenant_user (tenant_id, user_id),

    CONSTRAINT fk_role_permission_updated_by
        FOREIGN KEY (tenant_id, updated_by)
        REFERENCES tenant_user (tenant_id, user_id)
);

CREATE UNIQUE INDEX uq_tenant_role_permission
ON tenant_role_permissions (tenant_role_id, resource_action_mapping_id)
WHERE deleted_at IS NULL;

CREATE INDEX idx_role_permission_mapping
ON tenant_role_permissions (resource_action_mapping_id);


-- =========================================================
-- 8. USER ROLE ASSIGNMENT
-- Assigns a tenant user a role within a bistro and scope.
-- scope_unit_id references OrganizationUnit in Bistro Service.
-- =========================================================

CREATE TABLE user_role_assignment (
    assignment_id UUID PRIMARY KEY,

    tenant_id UUID NOT NULL,
    user_id UUID NOT NULL,

    bistro_id UUID NOT NULL,
    tenant_role_id UUID NOT NULL,

    scope_unit_id UUID NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by UUID NOT NULL,

    updated_at TIMESTAMPTZ,
    updated_by UUID,

    deleted_at TIMESTAMPTZ,

    CONSTRAINT fk_assignment_user
        FOREIGN KEY (tenant_id, user_id)
        REFERENCES tenant_user (tenant_id, user_id),

    CONSTRAINT fk_assignment_role
        FOREIGN KEY (tenant_id, bistro_id, tenant_role_id)
        REFERENCES tenant_role (tenant_id, bistro_id, tenant_role_id),

    CONSTRAINT fk_assignment_created_by
        FOREIGN KEY (tenant_id, created_by)
        REFERENCES tenant_user (tenant_id, user_id),

    CONSTRAINT fk_assignment_updated_by
        FOREIGN KEY (tenant_id, updated_by)
        REFERENCES tenant_user (tenant_id, user_id)
);

CREATE UNIQUE INDEX uq_user_role_assignment
ON user_role_assignment (
    user_id,
    bistro_id,
    scope_unit_id,
    tenant_role_id
)
WHERE deleted_at IS NULL;

CREATE INDEX idx_user_role_assignment
ON user_role_assignment (tenant_id, user_id, bistro_id);

CREATE INDEX idx_user_role_assignment_scope
ON user_role_assignment (bistro_id, scope_unit_id);
