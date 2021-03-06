package br.com.kontulari.kontschedule.atividade;

import br.com.kontulari.kontschedule.atividade.dto.AtividadeRepresentation;
import br.com.kontulari.kontschedule.atividade.dto.AtividadeUpdate;
import br.com.kontulari.kontschedule.contador.ContadorService;
import br.com.kontulari.kontschedule.empresa.EmpresaService;
import br.com.kontulari.kontschedule.exception.ContadorNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AtividadeMapper {

  public static Atividade fromDTO(AtividadeRepresentation atividadeDto)
      throws ContadorNotFoundException {
    return Atividade.builder()
        .id(atividadeDto.getId())
        .competencia(atividadeDto.getCompetencia())
        .dataExecucao(atividadeDto.getDataExecucao())
        .descricao(atividadeDto.getDescricao())
        .nome(atividadeDto.getNome())
        .prazoExecucao(atividadeDto.getPrazoExecucao())
        .status(atividadeDto.getStatus())
        .empresa(new EmpresaService().busca(atividadeDto.getIdEmpresa()))
        .contador(new ContadorService().busca(atividadeDto.getIdContador()))
        .setor(atividadeDto.getSetor())
        .build();
  }

  public static AtividadeRepresentation fromModel(Atividade atividade) {
    return AtividadeRepresentation.builder()
        .id(atividade.getId())
        .competencia(atividade.getCompetencia())
        .dataExecucao(atividade.getDataExecucao())
        .prazoExecucao(atividade.getPrazoExecucao())
        .descricao(atividade.getDescricao())
        .setor(atividade.getSetor())
        .nome(atividade.getNome())
        .status(atividade.getStatus())
        .idEmpresa(atividade.getEmpresa().getId())
        .idContador(atividade.getContador().getId())
        .build();
  }

  public static Atividade fromPut(AtividadeUpdate update) {
    return Atividade.builder()
        .id(update.getId())
        .descricao(update.getDescricao())
        .nome(update.getNome())
        .status(update.getStatus())
        .setor(update.getSetor())
        .build();
  }
}
