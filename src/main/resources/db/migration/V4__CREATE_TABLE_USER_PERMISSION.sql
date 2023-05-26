CREATE  TABLE IF NOT EXISTS users_permissions(
    id_user uuid NOT NULL,
    id_permission uuid NOT NULL,
    PRIMARY KEY (id_user, id_permission),
    FOREIGN KEY (id_user) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (id_permission) REFERENCES permissions(id) ON DELETE CASCADE
)