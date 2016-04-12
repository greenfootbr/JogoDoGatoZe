import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Plataforma here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Plataforma extends Actor
{
    
    
    private  Actor ator;
    private  Mundo1 mundo;
      
    
    
    /**
     * Act - do whatever the Plataforma wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        mundo = (Mundo1) getWorld();
        if(temAlguemAqui()){
         
    
         int ladoDireitoDoator = this.ator.getX() + (this.ator.getImage().getWidth()/2) - Mundo1.TAMANHO_DO_QUADRO;
         int ladoesquerdoDaPlataforma = 1 + this.getX() - (this.getImage().getWidth()/2);
            
          if( (ladoDireitoDoator - ladoesquerdoDaPlataforma) < 5 ){
                int x = this.ator.getX();
                int y = this.ator.getY();
              
              this.ator.setLocation(x-Mundo1.TAMANHO_DO_QUADRO,y);
              move(Mundo1.TAMANHO_DO_QUADRO);
              //mundo.oCenarioNaoPodeAtualizar();
          }
            
            
        
        }else{
            
            //mundo.oCenarioPodeAtualizar();
        
        }
        
        
        
        
    }   
    
    private boolean temAlguemAqui(){
    
        this.ator = getOneIntersectingObject(Actor.class);
        
        if(ator != null){
            return true;
        }
        
        return false;
        
    }
}
