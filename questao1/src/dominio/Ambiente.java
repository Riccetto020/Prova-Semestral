package dominio;

import java.math.BigDecimal;
import java.util.List;

public final class Ambiente {

    private final String nome;
    private final String provedor;
    private final String regiao;
    private final List<MaquinaVirtual> maquinas;
    private final Armazenamento armazenamento;
    private final Fila fila;

    Ambiente(String nome, String provedor, String regiao,
             List<MaquinaVirtual> maquinas, Armazenamento armazenamento, Fila fila) {
        this.nome = nome;
        this.provedor = provedor;
        this.regiao = regiao;
        this.maquinas = List.copyOf(maquinas);
        this.armazenamento = armazenamento;
        this.fila = fila;
    }

    public String nome() { return nome; }
    public String provedor() { return provedor; }
    public String regiao() { return regiao; }
    public List<MaquinaVirtual> maquinas() { return maquinas; }
    public Armazenamento armazenamento() { return armazenamento; }
    public Fila fila() { return fila; }

    public BigDecimal custoMensalEstimado() {
        BigDecimal vmPorHora = maquinas.isEmpty() ? BigDecimal.ZERO : maquinas.get(0).custoPorHora();
        return CalculoDeCusto.mensal(vmPorHora, maquinas.size(),
                armazenamento.custoPorGbMes(), armazenamento.gb(), fila.custoMensal());
    }
}
