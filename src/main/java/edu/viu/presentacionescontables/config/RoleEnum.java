package edu.viu.presentacionescontables.config;

import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Enum para declaracion de los roles
 */
public enum RoleEnum implements GrantedAuthority {

    ROLE_ADMIN(RoleCons.ROLE_ADMIN, RoleCons.VALOR_ADMIN, RoleCons.NAME_ADMIN),
    ROLE_FISCAL_GENERAL(RoleCons.ROLE_FISCAL_GENERAL, RoleCons.VALOR_FISCAL_GENERAL, RoleCons.NAME_FISCAL_GENERAL),
    ROLE_FISCAL(RoleCons.ROLE_FISCAL, RoleCons.VALOR_FISCAL, RoleCons.NAME_FISCAL),
    ROLE_CUENTADANTE(RoleCons.ROLE_CUENTADANTE, RoleCons.VALOR_CUENTADANTE, RoleCons.NAME_CUENTADANTE);

    private final String role;
    private final int valor;
    private final String nombre;

    RoleEnum(String role, int valor, String nombre) {
        this.role = role;
        this.valor = valor;
        this.nombre = nombre;
    }

    @Override
    public String getAuthority() {
        return role;
    }

    public String getRole() {
        return role;
    }

    public String getNombre() {
        return nombre;
    }

    public int getValor() {
        return valor;
    }

    public static List<String> obtenRolesHijos(String role) {
        List<String> respuesta;
        Optional<RoleEnum> roleBuscado = Stream.of(RoleEnum.values()).filter(roleEnum -> roleEnum.role.equals(role)).findAny();
        if (roleBuscado.isPresent()) {
            respuesta = Stream.of(RoleEnum.values()).filter(roleEnum -> roleEnum.valor >= roleBuscado.get().getValor()).map(RoleEnum::getAuthority).collect(Collectors.toList());
        } else {
            respuesta = new ArrayList<>();
        }
        return respuesta;
    }

    public static Map<String, String> obtenerTodosRoles() {
        return Stream.of(RoleEnum.values()).collect(Collectors.toMap(RoleEnum::getAuthority, RoleEnum::getNombre, (s, s2) -> s, LinkedHashMap::new));
    }

    public static String obtenDescripcionPorRole(String role) {
        String respuesta;
        Optional<RoleEnum> roleBuscado = Stream.of(RoleEnum.values()).filter(roleEnum -> roleEnum.role.equals(role)).findAny();
        if (roleBuscado.isPresent()) {
            respuesta = roleBuscado.get().getNombre();
        } else {
            respuesta = "";
        }
        return respuesta;
    }

    public static class RoleCons {
        public static final String ROLE_ADMIN = "ROLE_ADMIN";
        public static final String NAME_ADMIN = "Administador";
        public static final int VALOR_ADMIN = 0;

        public static final String ROLE_FISCAL_GENERAL = "ROLE_FISCAL_GENERAL";
        public static final String NAME_FISCAL_GENERAL = "Fiscal general";
        public static final int VALOR_FISCAL_GENERAL = 1;

        public static final String ROLE_FISCAL = "ROLE_FISCAL";
        public static final String NAME_FISCAL = "Fiscal";
        public static final int VALOR_FISCAL = 2;

        public static final String ROLE_CUENTADANTE = "ROLE_CUENTADANTE";
        public static final String NAME_CUENTADANTE = "Cuentadante";
        public static final int VALOR_CUENTADANTE = 3;
    }
}
