package org.softserveinc.dtos.enums;

/**
 * Enumeration of User Roles.
 */
public enum Role {

    ROLE_USER      (2),

    ROLE_ADMIN     (1),

    ROLE_ANONYMOUS (3);

    /**
     * The DB role Identifier
     */
    private Integer id;

    private Role(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
