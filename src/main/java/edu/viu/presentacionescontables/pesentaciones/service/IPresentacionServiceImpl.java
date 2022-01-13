package edu.viu.presentacionescontables.pesentaciones.service;

import edu.viu.presentacionescontables.convocatorias.entity.Convocatoria;
import edu.viu.presentacionescontables.convocatorias.service.IConvocatoriaService;
import edu.viu.presentacionescontables.municipios.entity.Municipio;
import edu.viu.presentacionescontables.municipios.service.IMunicipioService;
import edu.viu.presentacionescontables.pesentaciones.entity.Presentacion;
import edu.viu.presentacionescontables.pesentaciones.repository.IPresentacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class IPresentacionServiceImpl implements IPresentacionService {

    @Autowired
    private IPresentacionRepository presentacionRepository;

    @Autowired
    private IConvocatoriaService iConvocatoriaService;

    @Autowired
    private IMunicipioService iMunicipioService;


    @Override
    @Transactional(readOnly = true)
    public List<Presentacion> findAll() {
        return (List<Presentacion>) presentacionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Presentacion> buscarPorId(long id) {
        return presentacionRepository.findById(id);
    }

    @Override
    public void guardar(Presentacion presentacion) {
        presentacionRepository.save(presentacion);
    }

    @Override
    public void borrar(long id) {
        presentacionRepository.deleteById(id);
    }

    @Override
    public List<Convocatoria> buscarConvocatorias() {
        return iConvocatoriaService.buscarTodasConvocatorias();
    }

    @Override
    public List<Municipio> buscarMunicipios() {
        return iMunicipioService.buscarTodosMunicipios();
    }
}
