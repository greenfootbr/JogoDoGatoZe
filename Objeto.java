import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Classe para pai para todo ator não vivo do jogo.
 * 
 * @author  (Jhonatan Morais - jhonatanvinicius@gmail.com or jhonatan@dfjug.org) 
 * @version (1.0)
 */
abstract class Objeto extends Actor
{
    private  Personagem personagem;
    private  Mundo1 mundo;
    public static final int  LIMITE_DA_QUINA = 10;
    
    /**
     * Act - do whatever the Objeto wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        mundo = (Mundo1) getWorld();
        if(temAlguemAqui()  ){
            bloqueiaLadoEsquerdo();
            bloqueiaLadoDireito();
            bloqueiaQueda();
        }
    } 
    /**
     * Realiza a colisão esquerda-direita para impedir o avanço através do objeto
     */
    protected void bloqueiaLadoEsquerdo(){

        int ladoDireitoDoator = this.personagem.getX() + (this.personagem.getImage().getWidth()/2) - Mundo1.TAMANHO_DO_QUADRO;
        int ladoesquerdoDaPlataforma = 1 + this.getX() - (this.getImage().getWidth()/2);

        if( (ladoDireitoDoator - ladoesquerdoDaPlataforma) < 10 && personagem.estaIndoPraDireta()){

            mundo.oCenarioNaoPodeAtualizar();
            personagem.fiqueParado();

        }else{

            mundo.oCenarioPodeAtualizar();

        }

    }
    /**
     * Realiza a colisão direita-esquerda para impedir o avanço através do objeto
     */
    protected void bloqueiaLadoDireito(){

        int ladoEsquerdoDoator = this.personagem.getX() - (this.personagem.getImage().getWidth()/2) - Mundo1.TAMANHO_DO_QUADRO;
        int ladoDireitoDaPlataforma = 1 + this.getX() + (this.getImage().getWidth()/2);

        if( (ladoDireitoDaPlataforma - ladoEsquerdoDoator) < 10 && personagem.estaIndoPraEsquerda()){

            mundo.oCenarioNaoPodeAtualizar();
            personagem.fiqueParado();

        }else{

            mundo.oCenarioPodeAtualizar();

        }

    }
    /**
     * Realiza a colisão topo-fundo para impedir o avanço através do objeto
     */
    protected void bloqueiaQueda(){

        int peDoator = this.personagem.getY() + (this.personagem.getImage().getHeight()/2);
        int tetoDaPlataforma = this.getY() - (this.getImage().getHeight()/2);
        int tt = tetoDaPlataforma - peDoator;

        if( estaSobreMim(personagem) && (tetoDaPlataforma - peDoator < 0 )){

           mundo.oCenarioPodeAtualizar();
           //O espaço entre o pé e a plataforma é devido a imagem do gato, mostrar isso ao alunos
           int novaAltura =  peDoator - (this.personagem.getImage().getHeight()/2) - Mundo1.FORCA_DA_GRAVIDADE ; 
            personagem.setLocation(personagem.getX(),novaAltura);
            personagem.estaEmTerraFirme(); 
            //personagem.fiqueParado();

        }

    }
     /**
     * verifica se a colisão topo-fundo aconteceu
     */
    private boolean estaSobreMim(Personagem ator){
        
        int limiteEsquerdoDoAtor = ator.getX() - ator.getImage().getWidth()/2;
        int limiteDireitoDoAtor  = ator.getX() + ator.getImage().getWidth()/2;
        int meuLimiteEsquerdo = getX() - getImage().getWidth()/2 + LIMITE_DA_QUINA;
        int meuLimiteDireito  = getX() + getImage().getWidth()/2 - LIMITE_DA_QUINA;

        boolean teste1 = limiteEsquerdoDoAtor < meuLimiteDireito && limiteEsquerdoDoAtor > meuLimiteEsquerdo;
        boolean teste2 = limiteDireitoDoAtor > meuLimiteEsquerdo && limiteDireitoDoAtor < meuLimiteDireito;
        boolean teste3 = ator.getX() < meuLimiteDireito && ator.getX() > meuLimiteEsquerdo;

        if(teste1 || teste2 || teste3){
            return true;
        }
        return false; 

        
    }
    /**
     * verifica se houve um a colisão 
     */
    protected boolean temAlguemAqui(){
        this.personagem = (Personagem) getOneIntersectingObject(Personagem.class);
        if(personagem != null){
            return true;
        }

        return false;

    }
}
