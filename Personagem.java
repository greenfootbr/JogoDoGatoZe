import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Classe pai para todos os personagens vivos do jogo.
 * 
 * @author (Jhonatan Morais  - jhonatanvinicius@gmail.com or jhonatan@dfjug.org) 
 * @version (1.0)
 */
abstract class Personagem extends Actor
{
    //Constantes do Gato
    public static final int ALTURA_DO_PULO = 15; 
    public static final int TAMANHO_DO_PASSO = 1;

    //Variáveis de  controle dos movimentos
    protected int proximoPasso = 1;
    protected int alturaAtualDoPulo = 0;
    protected boolean estaPulando = false;
    protected boolean estaEmTerraFirme = true;
    protected boolean estaParado = true;
    protected boolean estaParaDireita = true;
    protected boolean estaParaEsquerda = false;

    /**
     * Act - do whatever the Personagens wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }

    /**
     * Utiliza o valor dos ciclos do cenário para criar o atraso necessário a atualização da sprite de movimento do personagem
     */
    abstract boolean possoAtualizar();

    /**
     * Controla os passos do personagem para saber qual deve ser a sprite a se utilizar para representar sua caminhada
     */
    abstract void marcarPasso();

    /**
     * Garante que ao ficar parado a sprite do personagem seja atualizada para a posição inicial
     */
    abstract void ficandoParado();

    /**
     * Retorna a imagem que representa cada ação do personagem
     */
    abstract GreenfootImage retornaImagem();

    /**
     * Gerencia o movimento do pulo desde o inicio(subida) até a sua aterrisagem
     */
    abstract void pulando();

    /**
     * Retornar verdadeiro se atualmente o personagem estiver caminhando para direita
     */
    public boolean estaIndoPraDireta(){

        if(proximoPasso >= 1 && proximoPasso < 7 && estaParaDireita && !estaParado ){
            return true;
        }
        return false;
    }

    /**
     * Retornar verdadeiro se atualmente o personagem estiver caminhando para esquerda
     */
    public boolean estaIndoPraEsquerda(){

        if(proximoPasso >= 7 && proximoPasso < 13 && estaParaEsquerda && !estaParado){
            return true;
        }
        return false;
    }

    /**
     * Atualiza a direção de caminhada do personagem para direita
     */
    public void caminheParaDireita(){

        estaParaDireita = true;
        estaParaEsquerda = false;
        estaParado = false;

    }

    /**
     * Atualiza a direção de caminhada do personagem para esquerda
     */
    public void caminheParaEsquerda(){
        estaParaDireita = false;
        estaParaEsquerda = true;
        estaParado = false;

    }

    /**
     * Faz o personagem parar de caminhar
     */
    public void fiqueParado(){
        estaParado = true;
    }
    
    /**
     * Informa ao personagem que ele esta em terra firme
     */
    public void estaEmTerraFirme(){
        //Acho que nao voi precisar mais , basta usar a resistencia de gravidade do objeto
        estaEmTerraFirme = true;
        estaPulando = false;
    }
    /**
     *  Verifica se o personagem esta no nível do solo
     */
    public boolean estaNoNivelDoSolo(){

        int alturaDosPes = getY() + getImage().getHeight()/2;

        if(  alturaDosPes - Mundo1.NIVEL_DO_SOLO == 0 ){
            return true;

        }
        return false;

    }
    /**
     *  Verifica se o personagem esta  acima do nível do solo
     */
    public boolean estaAcimaDoSolo(){

        int alturaDosPes = getY() + getImage().getHeight()/2;

        if(  alturaDosPes < Mundo1.NIVEL_DO_SOLO){
            return true;

        }
        return false;

    }
    /**
     *  Verifica se o personagem esta  abaixo do nível do solo
     */
    public boolean estaAbaixoDoSolo(){

        int alturaDosPes = getY() + getImage().getHeight()/2;

        if(  alturaDosPes > Mundo1.NIVEL_DO_SOLO){
            return true;

        }
        return false;

    }
    
    public int alturaAtual(){

        int alturaDosPes = getY() + getImage().getHeight()/2;
        int alturaAtual  = (alturaDosPes - Mundo1.NIVEL_DO_SOLO) *  -1;
        return alturaAtual;

    }
    
    
    
}
