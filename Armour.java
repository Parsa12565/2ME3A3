import java.util.Random;

public class Armour extends Loot {
	
	Random random=new Random();
	private int rarity;
	private static final String [] SLOTS={"Helm","Chest Piece","Gloves","Boots"};
	private static final int [][] RARITY_WEIGHTS={{3,2,1,0},{0,1,2,3}};
	private String slot;
	private String [] mods;
	
	public Armour(int enemyTier){
		int gearSlot = random.nextInt(SLOTS.length);
		slot=SLOTS[gearSlot];
		int r=0;
		for(int i=0;i<RARITIES.length;i++)
			r+=RARITY_WEIGHTS[enemyTier][i];
		r=random.nextInt(r);
		int w=0;
		for(int i=0;i<RARITIES.length;i++){
			w+=RARITY_WEIGHTS[enemyTier][i];
			if(r<w){
				rarity=i;
				break;
			}
		}
		mods=new String[rarity];
		for(int i=0;i<rarity;i++)
			mods[i]=getRandomAttribute();
	}
	
	private String getRandomAttribute(){
		int r = random.nextInt(ATTRIBUTES.length);
		return ATTRIBUTES[r]; 
	}
	
	private String getRarityDescription(){
		return RARITIES[rarity];
	}
	
	public String getDescription(){
		String descript=getRarityDescription()+" "+slot+":\n";
		for(int i=0;i<rarity;i++)
			descript=descript+mods[i]+"\n";
		return descript;
	}
	
}