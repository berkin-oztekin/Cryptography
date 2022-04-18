import java.util.*;

public class EncryptionAndDecryption {
    public static void main(String[] args) {

        char[] charArray = {'A', 'B', 'C', 'Ç', 'D', 'E', 'F', 'G', 'Ğ', 'H', 'I', 'İ', 'J', 'K', 'L', 'M',
                'N', 'O', 'Ö', 'P', 'R', 'S', 'Ş', 'T', 'U', 'Ü', 'V', 'Y', 'Z', '.', ',', '(', ')', '!', ';', ':', '\'', '“', '-',
                '?', '$', '@', '%', 'a', 'b', 'c', 'ç', 'd', 'e', 'f', 'g', 'ğ', 'h', 'ı', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'ö', 'p', 'r', 's', 'ş', 't', 'u', 'ü', 'v', 'y', 'z', 'Q', 'W', 'q', 'w', ' '};

        int[] IP = {6, 4, 2, 8, 7, 5, 3, 1};
        String key = "ww";

        String input = "How happy is the blameless vestal's lot!" +
                "The world forgetting, by the world forgot." +
                "Eternal sunshine of the spotless mind!" +
                "Each pray'r accepted, and each wish resign'd;" +
                "Labour and rest, that equal periods keep;" +
                "Obedient slumbers that can wake and weep;" +
                "Desires compos'd, affections ever ev'n," +
                "Tears that delight, and sighs that waft to Heav'n." +
                "Grace shines around her with serenest beams," +
                "And whisp'ring angels prompt her golden dreams.";


        // System.out.println(Arrays.deepToString(keyMatrix("GG", charArray)));

        System.out.println("HashTable");
        System.out.println(initializeHasTable(charArray));
        System.out.println("***************************");

        System.out.println("Reading input according to Hashtable");
        System.out.println(readInput(input , charArray));
        System.out.println("***************************");

        System.out.println("After initial permutations");
        System.out.println(manipulatedInput(input , IP , charArray));
        System.out.println("***************************");

        System.out.println("Reading chars for shifting");
        System.out.println(readingChars(input , IP ,charArray)); //0010110001001101001100100011010100110001000000010100000001001100
        System.out.println("***************************");

        System.out.println("4 block shifting Right");
        System.out.println(iterations(input ,IP ,charArray , 4));
        System.out.println("***************************");

        //Generating key
        System.out.println("Returning K2");
        System.out.println(returningKey2(key, charArray));
        System.out.println("********************************");
        System.out.println("Returning K3");
        System.out.println(returningKey3(key, charArray));
        System.out.println("********************************");
        System.out.println("Returning 8bit key of K2");
        System.out.println(Arrays.toString(returning8BitKey(returningKey2(key, charArray))));
        System.out.println("********************************");
        System.out.println("Returning 8bit key of K3");
        System.out.println(Arrays.toString(returning8BitKey(returningKey3(key, charArray))));
        System.out.println("********************************");
        System.out.println("XOR the 8bit Key2");
        System.out.println(XOR(returning8BitKey(returningKey2(key, charArray))));
        System.out.println("********************************");
        System.out.println("********************************");
        System.out.println("Returning 8bit key of K3");
        System.out.println(XOR(returning8BitKey(returningKey3(key, charArray))));
        System.out.println("********************************");
        System.out.println("********************************");
        System.out.println("Shift left rotate 3 K3");
        System.out.println(iterationsKey(3, XOR(returning8BitKey(returningKey3(key, charArray)))));



        //System.out.println(Arrays.toString(input.split(" ", input.length())));
    /*
        System.out.println(input.substring(8));
        System.out.println(input.length());

     */
        //readInput(input , charArray);


        // System.out.println(initializeHasTable(charArray));


        /*
        byte [] bytes = binarySummation(charArray.length).get(2).getBytes();
        String result = stringToBinary(bytes);
        System.out.println(Arrays.toString(result.getBytes()));

         */
        //System.out.println(initializeHasTable(charArray));
        //System.out.println(readInput(input ,charArray));
        //System.out.println(manipulatedInput(input , IP , charArray));

        /*
        System.out.println(initializeHasTable(charArray));
        System.out.println("***************************");
        System.out.println(readInput(input , charArray));
        System.out.println("***************************");
        System.out.println(manipulatedInput(input , IP , charArray));
        System.out.println("***************************");
        System.out.println(readingChars(input , IP ,charArray)); //0010110001001101001100100011010100110001000000010100000001001100
        System.out.println("***************************");
        System.out.println(iterations(input ,IP ,charArray));
        Arrays.toString(iterations(input, IP, charArray));

         */


    }


    public static ArrayList<String> binarySummation(int arrayLength) {
        ArrayList<String> binary = new ArrayList<String>();
        String input0 = "00000000";
        String input1 = "00000001";
        // Use as radix 2 because it's binary
        int number0 = Integer.parseInt(input0, 2);
        int number1 = Integer.parseInt(input1, 2);

        for (int i = 0; i <= arrayLength; i++) {
            number0 = number0 + number1;
            binary.add(Integer.toBinaryString(number0));
        }
        return binary;
    }

    public static HashMap<Object, Object> initializeHasTable(char[] charArray) {
        HashMap<Object, Object> charTable = new HashMap<Object, Object>();
        int x = 0;
        for (char i : charArray) {
            charTable.put(i, binarySummation(charArray.length).get(x));
            x++;
        }
        return charTable;
    }


    public static String readInput(String input, char[] charArray) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char m = input.charAt(i);


            if (initializeHasTable(charArray).get(m).toString().length() < 2)
                result.append("0000000").append((String) initializeHasTable(charArray).get(m)).append(" ");
            else if (initializeHasTable(charArray).get(m).toString().length() < 3) {
                result.append("000000").append((String) initializeHasTable(charArray).get(m)).append(" ");
            } else if (initializeHasTable(charArray).get(m).toString().length() < 4) {
                result.append("00000").append((String) initializeHasTable(charArray).get(m)).append(" ");
            } else if (initializeHasTable(charArray).get(m).toString().length() < 5) {
                result.append("0000").append((String) initializeHasTable(charArray).get(m)).append(" ");
            } else if (initializeHasTable(charArray).get(m).toString().length() < 6) {
                result.append("000").append((String) initializeHasTable(charArray).get(m)).append(" ");
            } else if (initializeHasTable(charArray).get(m).toString().length() < 7) {
                result.append("00").append((String) initializeHasTable(charArray).get(m)).append(" ");
            } else if (initializeHasTable(charArray).get(m).toString().length() < 8) {
                result.append("0").append((String) initializeHasTable(charArray).get(m)).append(" ");
            } else if (initializeHasTable(charArray).get(m).toString().length() < 9) {
                result.append((String) initializeHasTable(charArray).get(m)).append(" ");
            }
        }
        return result.toString();
    }

    public static void Decryption(String input, char[] charArray) {
        String words = readInput(input, charArray);
        String[] array = words.split(" ");

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            System.out.print(" ");
        }
        for (Map.Entry<Object, Object> entry : initializeHasTable(charArray).entrySet()) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].equals(entry.getValue())) {
                    System.out.print(entry.getKey());
                }
            }
        }
    }

    static public String initialPermutation(String input, int[] permutation) {
        String myNewString = "";
        for (int i = 1; i < input.length() + 1; i++) {
            for (int j = 0; j < input.length(); j++) {
                int positionNo = permutation[j];
                if (positionNo == i) {
                    myNewString = myNewString + input.charAt(j);
                }
            }
        }
        return myNewString;
    }

    static public ArrayList<Object> eightBlock(String input) {
        ArrayList<Object> arrayList = new ArrayList<Object>();
        int j = 0;
        for (int i = 8; i <= input.length(); i += 8) {
            String upToNCharacters = input.substring(j, i);
            arrayList.add(upToNCharacters);
            j += 8;
        }
        return arrayList;
    }

    static public String manipulatedInput(String input, int[] IP, char[] charArray) {
        String result = "";
        for (int i = 0; i < (input.length() / 8); i++) {
            result += readInput(initialPermutation((String) eightBlock(input).get(i), IP), charArray);
        }
        return result;
    }

    static public char[] readingChars(String input, int[] IP, char[] charArray) {
        int x = 0 ;
        String input1 = manipulatedInput(input, IP, charArray);
        String[] eightBit = input1.split(" ");
        char [] chars = new char[input1.length()];
        for(int j = 0 ; j < eightBit.length ; j++) {
            for (int i = 0; i < 8; i++) {
                chars[x] = eightBit[j].charAt(i);
                x++;
            }
        }

        return chars ;
    }
    public static void rightRotateByOne(char []arr , int n) {
        int i;
        char temp ;
        temp = arr[0];
        for (i = 0; i < arr.length - 1; i++)
            arr[i] = arr[i + 1];
        arr[arr.length-1] = temp;
    }
    public static void rightRotate(char [] arr, int d , int n) {
        for (int i = 0; i < d; i++)
            rightRotateByOne(arr , n);
    }
    public static char[] printArray(char arr[]) {
        char[] chars = new char[arr.length] ;
        for (int i = 0; i < arr.length; i++) {
            chars[i] = arr[i];
        }
        return chars ;
    }
    public static char[] iterations(String input , int [] IP , char [] charArray , int d){
        char [] array2;
        char [] array = readingChars(input ,IP ,charArray);
        rightRotate(array, d , 8 );
        array2 =printArray(array);
        return array2;
    }
    public static char[] iterationsKey(int d, String input){
        char[] chars  = input.toCharArray();
        char[] output ;
        rightRotate(chars, d , 8 );
        output=printArray(chars);
        return output;
    }
    public static char[] leftNibble(char [] array){
        char [] charsLeftNibble = new char[(array.length)+1];
        int x = 1 ;
        for (int i = 0 ; i <= array.length /2  ; i++){
            charsLeftNibble[i] = array[x];
            x = x+2;
        }
        return charsLeftNibble ;
    }
    public static char[] rightNibble(char [] array){
        char [] charsRightNibble = new char[(array.length/2)+1];
        int y = 0 ;
        for (int i = 0 ; i < (array.length/2) ; i++){
            charsRightNibble[i] = array[y];
            y = y+2;
        }
        return charsRightNibble ;
    }
    public static char[][] keyMatrix(String key , char [] array){
        int x = 0 ;
        String bitKey=readInput(key , array);
        String [] array1 = bitKey.split(" ");
        char [][] matrix =new char[4][4];
        char [] chars = new char[bitKey.length()-2];

        for(int i = 0 ; i < array1.length ; i++){
            for(int j = 0 ; j<8 ; j++){
                chars[x] = array1[i].charAt(j);
                x++;
                if (x > 19){
                    break;
                }
            }
        }
        int n = 0 ;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = chars[n];
                n++;
            }
        }
        return matrix;
    }

    public static char[] returningKey2(String key , char [] charArray){
        int x = 0 ;
       char [] key2 = new char[16];
       int [] k2 ={3,1,0, 2};
       char [][] matrix =keyMatrix(key , charArray);
       for(int i  =0 ; i < k2.length  ; i++){
             int m = k2[i];
             for(int j = 0 ; j< matrix.length ; j++){
                key2[x] = matrix[j][m] ;
                x++;
             }
       }
       return key2;//char[}
    }
    public static char[] returningKey3(String key , char [] charArray){
        int x = 0 ;
        char [] key3 = new char[16];
        int [] k2 ={0,1,3,2};
        char [][] matrix =keyMatrix(key , charArray);
        for(int i  =0 ; i < k2.length  ; i++){
            int m = k2[i];
            for(int j = 0 ; j< matrix.length ; j++){
                key3[x] = matrix[j][m] ;
                x++;
            }
        }
        return key3; //char[}
    }
    public static String[] returning8BitKey(char[] array) {
        int x = 0 ;
        char[] eightBitKey= array;
        char [] subKey1 =new char[eightBitKey.length/2];
        char [] subKey2 = new char[eightBitKey.length/2];
        for(int i = 0 ; i< eightBitKey.length/2 ;i++ ){
            subKey1[i] = eightBitKey[i];
        }
        for(int i = 8 ; i < eightBitKey.length; i++ ){
            subKey2[x] = eightBitKey[i];
            x++;
        }
        return new String[]{String.valueOf(subKey1),String.valueOf(subKey2)};//String []
    }
    public static String XOR( String[] array){
        String output = "";
        StringBuffer sb = new StringBuffer();
        String [] array1 =   array;
        String key1 = array1[0];
        String key2 = array1[1];
            for(int i = 0 ; i<8;i++){
                output  = sb.append(key1.charAt(i)^key2.charAt(i)).toString();
            }
            return output;
    }


}


