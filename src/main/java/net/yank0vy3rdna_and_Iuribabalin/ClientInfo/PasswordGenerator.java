package net.yank0vy3rdna_and_Iuribabalin.ClientInfo;

public class PasswordGenerator {
    private String[] character = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
            "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private  String[] numbers = {"1","2","3","4","5","6","7","8","9","0"};
//26
    public byte[] generator(){
        StringBuilder pass = new StringBuilder();
        for(int i = 0; i<8;i++){
            if(Math.random() > 0.7){
                pass.append(character[(int) (Math.random() * 25)].toUpperCase());
            }else if(Math.random() < 0.4){
                pass.append(character[(int) (Math.random() * 25)].toLowerCase());
            }else{
                pass.append(numbers[(int) (Math.random() * 9)]);
            }
        }
        return pass.toString().getBytes();
    }

}
