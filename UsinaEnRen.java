 public class UsinaEnRen extends Usina{
    private String fonteEnergia;

    public UsinaEnRen(String nome, double producaoMWh, double custoMWh, String fonteEnergia) {
        super(nome,producaoMWh,custoMWh);
        this.fonteEnergia = fonteEnergia;
    }

    @Override
    public double calculaPrecoMWh() {
        if(fonteEnergia.equals("Solar") || fonteEnergia.equals("solar")){
            return getCustoMWh()+(getCustoMWh()*0.25);
        }
        else if(fonteEnergia.equals("Eolica") || fonteEnergia.equals("eolica")){
            return getCustoMWh()+(getCustoMWh()*0.15);
        }
        else if(fonteEnergia.equals("Hidrica") || fonteEnergia.equals("hidrica")){
            return getCustoMWh()+(getCustoMWh()*0.05);
        }
        return 0;
    }

    @Override
    public String geraResumo() {
        String resumo = "1;" + getNome() + ";" + getProducaoMWh() + ";" + getCustoMWh() + ";" + fonteEnergia;
        return resumo;
    }

    public String getFonteEnergia() {
        return fonteEnergia;
    }

    public void setFonteEnergia(String fonteEnergia) {
        this.fonteEnergia = fonteEnergia;
    }
}
