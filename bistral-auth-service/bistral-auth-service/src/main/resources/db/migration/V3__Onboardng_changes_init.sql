
CREATE TYPE user_status AS ENUM(
    'INVITED',
    'ACTIVE',
    'REVOKED_INVITATION',
    'INACTIVE',
    'REVOKED_ACCESS',
    'DELETED'
);

ALTER TABLE tenant_user ADD COLUMN user_status user_status NOT NULL DEFAULT 'INVITED';