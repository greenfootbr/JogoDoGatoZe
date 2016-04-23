import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Classe responsável por criar e gerenciar o cenário do mundo 1 do jogo do gato Zé.
 * 
 * @author (Jhonatan Morais  - jhonatanvinicius@gmail.com or jhonatan@dfjug.org) 
 * @version (1.0)
 */
public class Mundo1 extends World
{

    //Constantes do Mundo
    public static final String  NOME_ARQUIVO_IMAGEM = "cenarios/mundo1/m1f1_";
    public static final String  EXTENSAO_ARQUIVO_IMAGEM = ".png";
    public static final String  IMAGEM_INICIAL_MUNDO1 = "cenarios/mundo1/mundo1.png";
    public static final String  MUSICA_DO_MUNDO1 = "Summer_Smile.mp3";
    public static final int     QUANTIDADE_DE_QUADROS = 350;
    public static final int     TAMANHO_DO_QUADRO = 4;
    public static final double  VELOCIDADE_ATUALIZACAO_QUADROS = 1;

    //Constantes da Natureza do mundo
    public static final int     LARGURA_CENARIO = 700;
    public static final int     ALTURA_CENARIO = 390;
    public static final int     NIVEL_DO_SOLO = 340;
    public static final int     FORCA_DA_GRAVIDADE = 5;

    //Variáveis de  controle do Jogo
    private int quadroAtual = 1;
    private int cicloAtual  = 0;
    private boolean oCenarioPodeAtualizar = true;

    //Objetos vivos do Jogo
    private GreenfootImage cenarioInicial;
    private Gato ze;
    private Objeto plataforma;

    /**
     * Contrutor do cenário.
     */
    public Mundo1()
    {    
        // Crio o Cenanario Inicial 
        super(LARGURA_CENARIO, ALTURA_CENARIO, 1); //Medidas do nosso cenário
        cenarioInicial = new GreenfootImage(IMAGEM_INICIAL_MUNDO1); //Crio a imagem inicial do cenário
        setBackground(cenarioInicial); //Coloco a imagem inicial dentro do nosso cenário

        //Crio os Objetos vivos
        ze = new  Gato();
        Instrucoes instrucoes = new  Instrucoes();

        //Colocos os objetos dentro do cenário cada
        addObject(ze, 83, alturaInicialDoSolo(ze));
        addObject(instrucoes, 602, 80);
        plataforma   = new Plataforma();
        addObject(plataforma, 336, alturaInicialDoSolo(plataforma));
        addObject(new Plataforma(), 434, 298);
        addObject(new Plataforma(), 564, 276);
        addObject(new Plataforma(), 650,203);

    }

    /**
     * Retorna o valor do ciclo atual que varia de 0 a 1000, é útil para realizar cálculos secundários
     */
    public int getCiclo(){
        return cicloAtual;
    }

    /**
     * Atualiza o valor do ciclo até 1000, quando a contagem é então recomeça do 0
     */
    private void contaCiclo(){
        cicloAtual++;
        if(cicloAtual > 1000){
            cicloAtual = 0;
        }

    }

    /**
     * Executa recursivamente todas as ações de responsabilidade do mundo
     */
    public void act()
    {
        //valido se o cenário deve ou não ser atualizado com a proxima cena
        if(ze.estaIndoPraDireta() || ze.estaIndoPraEsquerda()  ){

            projetor( proximaCena()); 

            atualizaObjetosdoCenario();
        } 

        contaCiclo();
        aplicarForcaDaGravidade();
    }

    /**
     * Solicita que o cenário seja atualizado com a próxima cena
     */
    private void projetor(GreenfootImage proximaCena){
        // Método que atualiza a imagem do cenário
        setBackground( proximaCena); 
    }

    /**
     * Retorna a próxima cena do filme de imagens
     */
    private GreenfootImage proximaCena(){

        GreenfootImage proximaCena = filme(); //Pego a proxima cena do filme
        if(oCenarioPodeAtualizar){  
            adiantaFilme();  
            rebobinaFilme();
        }
        return proximaCena;        
    }

    /**
     * Verifica que o quadro atual já chegou ao limite de quadros da fita, se sim reinicia para o quadro inicial correspondente
     */
    private void rebobinaFilme(){
        if(this.quadroAtual > QUANTIDADE_DE_QUADROS){
            this.quadroAtual = 1;
        }else if(this.quadroAtual <= 0){
            this.quadroAtual = QUANTIDADE_DE_QUADROS;
        }

    }

    /**
     * Atualizo a direção em que a cena foi atualizada, se o herói foi pra direita o quadro atual avança, se para esquerda retrocede
     */
    private void adiantaFilme(){

        if(ze.estaIndoPraDireta()){
            this.quadroAtual += VELOCIDADE_ATUALIZACAO_QUADROS;
        }else{
            this.quadroAtual -= VELOCIDADE_ATUALIZACAO_QUADROS;
        }

    }

    /**
     * Cria e monta a próxima cena quadro a quadro e retorna a imagem pronta
     */
    private GreenfootImage filme(){
        GreenfootImage novaCena = new GreenfootImage(getWidth(),getHeight()); //Crio a moldura da nova imagem do cenário
        int posicaoDoQuadro = 0; //será a posição da fileta(slice/quadros) dentro da imagem do cenário
        int quadro = this.quadroAtual; // Pego o valor para saber apartir de qual fileta devo começar a criar a imagem
        while(posicaoDoQuadro < novaCena.getWidth()){ // Começo a criar a imagem

            if(quadro > QUANTIDADE_DE_QUADROS){ // Verifico se ja chequei ao final do filme, se sim reinicio do quadro inicial
                quadro =1; 
            }

            novaCena.drawImage(imagemDoNovoQuadro(quadro), posicaoDoQuadro, 0); //Coloco a fileta dentro da moldura da nova imagem
            quadro++; // atualizo para o valor da proxima fileta
            posicaoDoQuadro += TAMANHO_DO_QUADRO; // atualizo para a posição da nova fileta
        }

        return novaCena; // devolvemos a moldura da nova imagem totalmente preenchida com as filetas(quadros)
    }

    /**
     * Retorna o quadro/fileta/slice solicitado de dentro da pasta de imagens
     */
    private GreenfootImage imagemDoNovoQuadro(int proximoQuadro){
        String nomeDoArquivo = NOME_ARQUIVO_IMAGEM + proximoQuadro + EXTENSAO_ARQUIVO_IMAGEM;
        GreenfootImage novoquadro = new GreenfootImage(nomeDoArquivo);
        return novoquadro;
    }

    /**
     * Atualizo a posição dos objetos do jogo sempre que a cena for atualizada, se o herói foi pra direita a posição do objeto diminui, se para esquerda avança
     */
    private void atualizaObjetosdoCenario(){

        List<Plataforma> listaDePlataformas = getObjects(Plataforma.class);

        if(ze.estaIndoPraDireta() && oCenarioPodeAtualizar){
            for(Objeto plataforma : listaDePlataformas){
                plataforma.move(TAMANHO_DO_QUADRO * -1);
            }

        }
        if(ze.estaIndoPraEsquerda() && oCenarioPodeAtualizar){
            for(Objeto plataforma : listaDePlataformas){
                plataforma.move(TAMANHO_DO_QUADRO );
            }
        }

    }
    /**
     * Solicita ao cenário para parar de atualizar sua movimentação
     */    
    public void pareDeAtualizarOCenario(){

        if(ze.estaIndoPraDireta() && oCenarioPodeAtualizar){
            plataforma.move(TAMANHO_DO_QUADRO * -1);
        }
        if(ze.estaIndoPraDireta() && oCenarioPodeAtualizar){
            plataforma.move(TAMANHO_DO_QUADRO );
        }

    }
    /**
     * Solicita ao cenário para voltar atualizar sua movimentação
     */  
    public void oCenarioPodeAtualizar(){

        oCenarioPodeAtualizar = true;

    }

    public void oCenarioNaoPodeAtualizar(){

        oCenarioPodeAtualizar = false;

    }

    /**
     * Retorna a altura inicial do solo para o ator solicitado
     */
    private int alturaInicialDoSolo(Actor ator){

        return NIVEL_DO_SOLO - ator.getImage().getHeight()/2;

    }

    /**
     * Aplica a gravidade em todos os atores do tipo personagem
     */
    public void aplicarForcaDaGravidade(){

        List<Personagem>  listaDePersonagem = getObjects(Personagem.class);
        for(Personagem ator : listaDePersonagem){
            int alturaDoGato = ator.alturaAtual();
            int alturaIicialSolo = alturaInicialDoSolo(ator);
            if(alturaDoGato > 0)  {  //temporario
                ator.setLocation(ator.getX(), ator.getY() + FORCA_DA_GRAVIDADE);
            }
            if(ator.getY() >= (ALTURA_CENARIO -1) || ator.getY() <= 0 ){

                removeObject(ator);

            }

        }

    }

}

