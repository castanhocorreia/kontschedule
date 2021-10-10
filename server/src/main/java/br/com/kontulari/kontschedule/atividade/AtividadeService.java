package br.com.kontulari.kontschedule.atividade;

import br.com.kontulari.kontschedule.atividade.dto.AtividadeRepresentation;
import br.com.kontulari.kontschedule.exception.AtividadeNotFoundException;
import br.com.kontulari.kontschedule.exception.ContadorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtividadeService {
  @Autowired private AtividadeRepository repository;

  public AtividadeRepresentation busca(Long id) throws AtividadeNotFoundException {
    Atividade atividade = verifyIfExists(id);

    return AtividadeMapper.fromModel(atividade);
  }

  private Atividade verifyIfExists(Long id) throws AtividadeNotFoundException {
    return repository.findById(id).orElseThrow(() -> new AtividadeNotFoundException(id));
  }

  public AtividadeRepresentation cadastra(@Valid AtividadeRepresentation registro)
      throws ContadorNotFoundException {
    Atividade atividade = AtividadeMapper.fromDTO(registro);
    repository.save(atividade);
    return AtividadeMapper.fromModel(atividade);
  }

  public void deleta(Long id) throws AtividadeNotFoundException {
    verifyIfExists(id);
    repository.deleteById(id);
  }

  public List<AtividadeRepresentation> buscaPorEmpresa(Long empresaId) {
    List<Atividade> atividades = repository.findByEmpresaId(empresaId);
    return atividades.stream().map(AtividadeMapper::fromModel).collect(Collectors.toList());
  }
}
