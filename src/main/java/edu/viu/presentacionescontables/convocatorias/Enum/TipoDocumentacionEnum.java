package edu.viu.presentacionescontables.convocatorias.Enum;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TipoDocumentacionEnum {

    LIBRO_DIARIO(0, "Libro Diario"),
    LIBRO_MAYOR(1, "Libro Mayor"),
    BALANCE_SUMAS_SALDOS(2, "Balance de Sumas y Saldos"),
    REGISTRO_INGRESO_CAJA(3, "Registro de Ingreso de Caja"),
    REGISTRO_MOVIMIENTOS_BANCOS(3, "Registro de Movimientos de Bancos");

    private final int codigo;
    private final String descipcion;

    TipoDocumentacionEnum(int codigo, String descipcion) {
        this.codigo = codigo;
        this.descipcion = descipcion;
    }

    public static Map<Integer, String> obtenerTodosTipos() {
        return Stream.of(TipoDocumentacionEnum.values()).collect(Collectors.toMap(TipoDocumentacionEnum::getCodigo, TipoDocumentacionEnum::getDescipcion, (s, s2) -> s, LinkedHashMap::new));
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescipcion() {
        return descipcion;
    }

    public static String obtenDescripcionPorRole(Integer role) {
        String respuesta;
        Optional<TipoDocumentacionEnum> roleBuscado = Stream.of(TipoDocumentacionEnum.values()).filter(roleEnum -> roleEnum.getCodigo() == role).findAny();
        if (roleBuscado.isPresent()) {
            respuesta = roleBuscado.get().getDescipcion();
        } else {
            respuesta = "";
        }
        return respuesta;


    }
}
