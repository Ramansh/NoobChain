import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class NoobChain {

    public static ArrayList<Block> blockChain = new ArrayList<Block>();

    private static int difficulty = 1;

    public static void main(String[] args) {

        System.out.println("Trying to mine block 1....");
        addBlock(new Block("This is the first block", "0"));

        System.out.println("Trying to mine block 2....");
        addBlock(new Block("Second hun mai", blockChain.get(blockChain.size()-1).hash));

        System.out.println("Trying to mine block 3....");
        addBlock(new Block("Teesra daaba", blockChain.get(blockChain.size()-1).hash));

        System.out.println("\nBlock chain is valid : " + isChainValid());

        String blockChainJson = StringUtil.getJson(blockChain);
        System.out.println("\nThe block chain : ");
        System.out.println(blockChainJson);
    }

    public static boolean isChainValid() {

        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0','0');

        //loop through blockchain to check hashes
        for(int i = 1; i < blockChain.size(); i++) {

            currentBlock = blockChain.get(i);
            previousBlock = blockChain.get(i-1);

            //compare registered hash with calculated hash
            if(!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println(i);
                System.out.println("\nCurrent Hashes not equal");
                return false;
            }

            //compare previous hash and registered previous hash
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("\nPrevious Hashes not equal");
                return false;
            }

            //check if hash is solved
            if(!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("\nThis block hasn't been mined.");
                return false;
            }
        }

        return true;
    }

    public static void addBlock(Block newBlock) {
        newBlock.mineBlock(difficulty);
        blockChain.add(newBlock);
    }
}
