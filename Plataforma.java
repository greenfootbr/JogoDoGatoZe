import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Plataforma here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Plataforma extends Actor
{

    private  Personagem personagem;
    private  Mundo1 mundo;

    
    /**
     * Act - do whatever the Plataforma wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        mundo = (Mundo1) getWorld();
        if(temAlguemAqui()  ){

            int ladoDireitoDoator = this.personagem.getX() + (this.personagem.getImage().getWidth()/2) - Mundo1.TAMANHO_DO_QUADRO;
            int ladoesquerdoDaPlataforma = 1 + this.getX() - (this.getImage().getWidth()/2);

            if( (ladoDireitoDoator - ladoesquerdoDaPlataforma) < 10 && personagem.estaIndoPraDireta()){

                //int x = this.personagem.getX();
                //int y = this.personagem.getY();

                mundo.oCenarioNaoPodeAtualizar();
                //this.ator.setLocation(x-Mundo1.TAMANHO_DO_QUADRO,y);
                //move(Mundo1.TAMANHO_DO_QUADRO);

            }else{

                mundo.oCenarioPodeAtualizar();

            }

        
        }

        
    }   
    private boolean temAlguemAqui(){
        this.personagem = (Personagem) getOneIntersectingObject(Personagem.class);
        if(personagem != null){
            return true;
        }

        return false;

    }
}
