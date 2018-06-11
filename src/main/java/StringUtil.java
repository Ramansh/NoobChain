import com.google.gson.GsonBuilder;

import java.security.MessageDigest;

public class StringUtil {

    //applies sha256 hash to the string and returns the result
    public static String applySHA256(String data) {

        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            //applies sha256 to our input
            byte[] hash = messageDigest.digest(data.getBytes("UTF-8"));
            //this will contain hash as hexadecimal
            StringBuffer hexString = new StringBuffer();

            for(int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length()==1)
                    hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //function to turn object to json string
    public static String getJson(Object o) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(o);
    }

    //Returns difficulty string target, to compare to hash. eg difficulty of 5 will return "00000"
    public static String getDifficultyString(int difficulty) {
        return new String(new char[difficulty]).replace('\0', '0');
    }

}
