package Model;

/* 
  Esta classe implementa um objeto do tipo time, contendo informações como nome, saldo de gols.
 */
public class Team {
    
    private String name;
    private float attackValue, defenseValue;
    private int matchCount;
    public static int ID=1;
    
    public Team(String name){
        this.name = name;
        attackValue = 0;
        defenseValue = 0;
        matchCount = 0;
        this.ID = ID++;
    }
    
    /*Essa função faz adições no valor de ataque de um time. O valor de ataque é sempre definido pelo saldo de gols dividido pelo 
      número de partidas jogadas, guardado na variável matchCount.*/
    
    public float addAttackValue(int attackAdder){
        
        float fullValue = (float)(this.attackValue*matchCount);
        fullValue = fullValue + attackAdder;
        attackValue = (fullValue/(++matchCount));
        
        return this.attackValue; //Retorna o novo valor de ataque
    }
    
    /*Essa função faz adições no valor de defesa de um time. O valor de defesa é sempre definido pelo saldo de gols recebidos
     dividido pelo número de partidas jogadas. O saldo defensivo de um time é sempre negativo, ou seja, o valor é sempre subtraído.
     Apesar disso, a entrada deve ser positiva. */
    
    public float addDefenseValue(int defenseAdder){
        
        float fullValue = (float)(this.defenseValue*matchCount);
        fullValue = fullValue - defenseAdder;
        defenseValue = (fullValue/(++matchCount));
        
        return this.defenseValue; //Retorna o novo valor de defesa
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(float attackValue) {
        this.attackValue = attackValue;
    }

    public float getDefenseValue() {
        return defenseValue;
    }

    public void setDefenseValue(float defenseValue) {
        this.defenseValue = defenseValue;
    }
    
    
    public int getMatchCount(){
        return matchCount;
    }
    
    public int setMatchCount(int matchCount){
        return this.matchCount = matchCount;
    }
    
    
    public int getID(){
        return this.ID;
    }
    
    
    
    
}
