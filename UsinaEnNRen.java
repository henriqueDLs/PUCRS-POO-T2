public class UsinaEnNRen extends Usina{
    private String combustivel;
    private int durabilidadeCombustivel;

    public UsinaEnNRen(String nome, double producaoMWh, double custoMWh, String combustivel, int durabilidadeCombustivel) {
        super(nome,producaoMWh,custoMWh);
        this.combustivel = combustivel;
        this.durabilidadeCombustivel = durabilidadeCombustivel;
    }

    @Override
    public double calculaPrecoMWh() {
        if(combustivel.equals("Petroleo") || combustivel.equals("petroleo")){
            return getCustoMWh()+(getCustoMWh()*0.30);
        }
        else if(combustivel.equals("Carvao") || combustivel.equals("carvao")){
            return getCustoMWh()+(getCustoMWh()*0.20);
        }
        else if(combustivel.equals("Nuclear") || combustivel.equals("nuclear")){
            return getCustoMWh()+(getCustoMWh()*0.10);
        }
        return 0;
    }

    @Override
    public String geraResumo() {
        String resumo = "2;" + getNome() + ";" + getProducaoMWh() + ";" + getCustoMWh() + ";" + combustivel + ";" + durabilidadeCombustivel;
        return resumo;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public int getDurabilidadeCombustivel() {
        return durabilidadeCombustivel;
    }

    public void setDurabilidadeCombustivel(int durabilidadeCombustivel) {
        this.durabilidadeCombustivel = durabilidadeCombustivel;
    }
}
