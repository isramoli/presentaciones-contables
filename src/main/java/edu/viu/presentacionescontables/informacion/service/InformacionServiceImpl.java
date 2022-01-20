package edu.viu.presentacionescontables.informacion.service;

import edu.viu.presentacionescontables.convocatorias.entity.Convocatoria;
import edu.viu.presentacionescontables.convocatorias.service.IConvocatoriaService;
import edu.viu.presentacionescontables.pesentaciones.service.IPresentacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InformacionServiceImpl implements IInformacionService {

    @Autowired
    private IConvocatoriaService convocatoriaService;

    @Autowired
    private IPresentacionService presentacionService;

    @Override
    @Transactional(readOnly = true)
    public List<Convocatoria> buscarTodasConvocatorias() {

        List<Convocatoria> listaConvocatorias = convocatoriaService.buscarTodasConvocatorias();
        for (Convocatoria convocatoria : listaConvocatorias) {
            presentacionService.buscarPorConvocatorias(convocatoria);
            System.out.println(1);
        }
        return convocatoriaService.buscarTodasConvocatorias();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Convocatoria> buscarPorNombre(String nombre) {
        return convocatoriaService.buscarPorNombre(nombre);
    }

    @Override
    public void guardar(Convocatoria convocatoria) {
        convocatoriaService.guardar(convocatoria);
    }

    @Override
    public void borrar(String nombre) {
        convocatoriaService.borrar(nombre);
    }
}