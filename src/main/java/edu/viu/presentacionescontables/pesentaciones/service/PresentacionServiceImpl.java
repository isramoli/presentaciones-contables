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
public class PresentacionServiceImpl implements IPresentacionService {

    @Autowired
    private IPresentacionRepository presentacionRepository;

    @Autowired
    private IConvocatoriaService iConvocatoriaService;

    @Autowired
    private IMunicipioService iMunicipioService;

    /**
     * Busca todas las presentaciones
     *
     * @return Devuelve el listado de presentaciones
     */
    @Override
    @Transactional(readOnly = true)
    public List<Presentacion> findAll() {
        return (List<Presentacion>) presentacionRepository.findAll();
    }

    /**
     * Busca una presentación por id
     *
     * @param id Identificador de la presentación
     * @return Devuelve la presentación
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Presentacion> buscarPorId(long id) {
        return presentacionRepository.findById(id);
    }

    /**
     * Guarda la presentación
     *
     * @param presentacion Presentación a guardar
     */
    @Override
    public void guardar(Presentacion presentacion) {
        presentacionRepository.save(presentacion);
    }

    /**
     * Borra la presentación
     *
     * @param id Identificador de la presentación
     */
    @Override
    public void borrar(long id) {
        presentacionRepository.deleteById(id);
    }

    /**
     * Devuelve todas las convocatorias
     *
     * @return Listado todas las convocatorias
     */
    @Override
    public List<Convocatoria> buscarConvocatorias() {
        return iConvocatoriaService.buscarTodasConvocatorias();
    }

    /**
     * Devuelve todos los municipios
     *
     * @return Listado de los municipios
     */
    @Override
    public List<Municipio> buscarMunicipios() {
        return iMunicipioService.buscarTodosMunicipios();
    }

    /**
     * Busca la presentación de una convocatoria
     * @param convocatoria Convocatoria por la que buscar
     * @return Devuelve la presentación buscada
     */
    @Override
    public Presentacion buscarPorConvocatorias(Convocatoria convocatoria) {
        return presentacionRepository.buscarPorConvocatoria(convocatoria.getNombre());
    }
}
