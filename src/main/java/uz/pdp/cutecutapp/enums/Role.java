package uz.pdp.cutecutapp.enums;

import lombok.Getter;

@Getter
public enum Role {
    SUPER_ADMIN,
    ADMIN,
    BARBER,
    CLIENT;

    public Role checkRole(String role){
        for (Role value : values()) {
            if (value.name().equalsIgnoreCase(role)){
                return value;
            }
        }
        throw new RuntimeException("role not found");
    }
}
