import java.util.Random;

public class CAI {
	
	//---  Constants   ----------------------------------------------------------------------------
	private static final String [] PROTOCOLS = {"Cerebus","Ouroboros"};

	private static final int SIZE_OUROBOROS_KEYS = 2;
	private static final int SIZE_CEREBUS_KEYS = 2;
	private static final int [] SIZE_KEYS={SIZE_CEREBUS_KEYS,SIZE_OUROBOROS_KEYS};

	private static final int NUM_OF_CEREBUS_SITES=3;
	private static final int NUM_OF_OUROBOROS_SITES=3;
	private int [][] securitySite={new int[NUM_OF_CEREBUS_SITES],new int[NUM_OF_OUROBOROS_SITES]};
	
	private Random rand;
	
	//---  Constructors   -------------------------------------------------------------------------
		
	public CAI() {
		rand = new Random();
	}	
		
	//---  Operations   ---------------------------------------------------------------------------
	
	public void simulateCAI(){
		for(int i=0;i<securitySite.length;i++) 
			for(int j=0;j<securitySite[i].length;j++) 
				assignSiteKey(i,j,rand.nextInt(SIZE_KEYS[i]));
	}
	
	public String receiveMessage(String Protocol,int N,String in) {
		int P;
		for(P=0;P<PROTOCOLS.length;P++)
			if(PROTOCOLS[P].equals(Protocol))
				break;
		return decryptMessage(P,securitySite[P][N], in);
	}

	public String sendMessage(String Protocol,int N,String in) {
		int P;
		for(P=0;P<PROTOCOLS.length;P++)
			if(PROTOCOLS[P].equals(Protocol))
				break;
		return encryptMessage(P,securitySite[P][N], in);
	}
		
	//---  Setter Methods   -----------------------------------------------------------------------
	
	private void assignSiteKey(int P,int N,int type) {
		securitySite[P][N] = type;
	}

	//---  Helper Methods   -----------------------------------------------------------------------
	
	private String encryptMessage(int P,int type, String message) {
		return EncryptionProtocols.encrypt(PROTOCOLS[P],type,message);
	}
	
	private String decryptMessage(int P,int type, String message) {
		return EncryptionProtocols.decrypt(PROTOCOLS[P],type,message);
	}
		
}

