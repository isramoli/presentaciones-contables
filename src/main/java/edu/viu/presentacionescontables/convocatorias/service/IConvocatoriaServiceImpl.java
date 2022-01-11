package edu.viu.presentacionescontables.convocatorias.service;

import edu.viu.presentacionescontables.convocatorias.entity.Convocatoria;
import edu.viu.presentacionescontables.convocatorias.repository.IConvocatoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class IConvocatoriaServiceImpl implements IConvocatoriaService {

    @Autowired
    private IConvocatoriaRepository ConvocatoriaRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<Convocatoria> buscarTodasConvocatorias() {
        return (List<Convocatoria>) ConvocatoriaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Convocatoria> buscarPorNombre(String nombre) {
        return ConvocatoriaRepository.findById(nombre);
    }

    @Override
    public void guardar(Convocatoria Convocatoria) {
        ConvocatoriaRepository.save(Convocatoria);
    }

    @Override
    public void borrar(String nombre) {
        ConvocatoriaRepository.deleteById(nombre);
    }


}
