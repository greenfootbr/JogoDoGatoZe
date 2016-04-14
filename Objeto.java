import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Objeto here.
 * 
 * @author  (Jhonatan Morais - jhonatanvinicius@gmail.com or jhonatan@dfjug.org) 
 * @version (1.0)
 */
abstract class Objeto extends Actor
{
    private  Personagem personagem;
    private  Mundo1 mundo;
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
    
    protected void bloqueiaQueda(){

        int peDoator = this.personagem.getY() + (this.personagem.getImage().getHeight()/2);
        int tetoDaPlataforma = this.getY() - (this.getImage().getHeight()/2);

        if( (tetoDaPlataforma - peDoator) < 10 ){

            mundo.oCenarioPodeAtualizar();
            
            //personagem.setLocation(personagem.getX(), tetoDaPlataforma);
            personagem.paraDeCair();
            //personagem.fiqueParado();

        }

    }
    

    protected boolean temAlguemAqui(){
        this.personagem = (Personagem) getOneIntersectingObject(Personagem.class);
        if(personagem != null){
            return true;
        }

        return false;

    }
}
