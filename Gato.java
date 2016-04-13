import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Classe para Criar o personagem do Gato Zé
 * 
 * @author (Jhonatan Morais - jhonatanvinicius@gmail.com or jhonatan@dfjug.org) 
 * @version (1.0)
 */
public class Gato extends Personagem
{
   

    /**
     * Act - do whatever the Ze wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(Greenfoot.isKeyDown("right")){
            caminheParaDireita();
            marcarPasso();
            setImage(retornaImagem());
            if(getX() < getWorld().getWidth()/3 || estaNoAr){
                 move(TAMANHO_DO_PASSO);
            }
        }

        if(Greenfoot.isKeyDown("left")){
            caminheParaEsquerda();
            marcarPasso();
            setImage(retornaImagem());
            if(getX() > getWorld().getWidth()/3|| estaNoAr){
                  move(TAMANHO_DO_PASSO *(-1));
            }
        }

        if(Greenfoot.isKeyDown("space")){
            estaNoAr = true;
        }

        if(estaNoAr){
            pulando();
        }

        ficandoParado();

    } 

    /**
     * Utiliza o valor dos ciclos do cenário para criar o atraso necessário a atualização da sprite de movimento do personagem
     */
    
    protected boolean possoAtualizar(){
        Mundo1 mundo = (Mundo1) getWorld();
        return (mundo.getCiclo()% (TAMANHO_DO_PASSO * Mundo1.TAMANHO_DO_QUADRO + Mundo1.TAMANHO_DO_QUADRO/2) ) == 0;
    }

    

    /**
     * Controla os passos do personagem para saber qual deve ser a sprite a se utilizar para representar sua caminhada
     */
    protected void marcarPasso(){

        if(estaParaDireita ){

            if(possoAtualizar()){
                proximoPasso++;
            } 

            if(proximoPasso > 6){
                proximoPasso = 1;
            }

        }else if(estaParaEsquerda){

            if(possoAtualizar()){ proximoPasso++;}
            if(proximoPasso < 7 || proximoPasso > 12 ){
                proximoPasso = 7;
            }

        }

    }

    /**
     * Garante que ao ficar parado a sprite do personagem seja atualizada para a posição inicial
     */
    protected void ficandoParado(){
        if(proximoPasso >= 1 && proximoPasso < 7 && estaParaDireita && !Greenfoot.isKeyDown("right")){
            proximoPasso = 1;
            setImage(retornaImagem());
            fiqueParado();
        }
        if(proximoPasso > 6 && proximoPasso < 13 && estaParaEsquerda && !Greenfoot.isKeyDown("left")){
            proximoPasso = 7;
            setImage(retornaImagem());
            fiqueParado();
        }

    }

    /**
     * Retorna a imagem que representa cada ação do personagem
     */
    protected GreenfootImage retornaImagem(){
        //Retorna imagem parado e virado para direita
        if(estaParaDireita && alturaAtual == 0){
            return new GreenfootImage("images/persons/ze/ze_"+proximoPasso+".png");
        }
        //Retorna imagem pulando/caindo lado direito
        if(estaParaDireita && alturaAtual > 0){
            return new GreenfootImage("images/persons/ze/ze_2.png");
        }
        //Retorna imagem parado e virado para esquerda
        if(estaParaEsquerda && alturaAtual == 0){
            return new GreenfootImage("images/persons/ze/ze_"+proximoPasso+".png");
        }
        //Retorna imagem pulando/caindo lado esquerda
        if(estaParaEsquerda && alturaAtual > 0){
            return new GreenfootImage("images/persons/ze/ze_8.png");
        }

        return new GreenfootImage("images/persons/ze/ze_1.png");
    }

    /**
     * Gerencia o movimento do pulo desde o inicio(subida) até a sua aterrisagem
     */
    protected void pulando(){

        if(alturaAtual < ALTURA_DO_PULO && estaNoSolo){
            setImage(retornaImagem());
            setLocation(getX(), getY()-4);
            alturaAtual+=2;

            if(alturaAtual == ALTURA_DO_PULO){
                estaNoSolo = false;
            }

        }else if(alturaAtual != 0 ){
            setLocation(getX(), getY()+4);
            alturaAtual-=2;

            if(alturaAtual == 0){
                estaNoAr = false;
                estaNoSolo = true;
                setImage(retornaImagem());
            }

        }       

    }

    

}

